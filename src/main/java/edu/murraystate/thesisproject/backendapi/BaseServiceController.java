package edu.murraystate.thesisproject.backendapi;

import edu.murraystate.thesisproject.backendapi.core.EvaluateDifference;
import edu.murraystate.thesisproject.backendapi.core.Formatter;
import edu.murraystate.thesisproject.backendapi.core.utils.types.SegmentLiteral;
import edu.murraystate.thesisproject.backendapi.core.utils.types.SegmentMeta;
import edu.murraystate.thesisproject.backendapi.records.BulkEvalData;
import edu.murraystate.thesisproject.backendapi.records.Status;
import edu.murraystate.thesisproject.backendapi.records.receivables.metaFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class BaseServiceController {

    private static int statusCode = 1;
    private static String statusText = "Operational";
    private final AtomicLong fileCounter = new AtomicLong();

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public String error() {
        return "<html>\n" + "<header><title>Error</title></header>\n" +
                "<body>\n" + "An internal error has occurred.\n" + "</body>\n" + "</html>";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api-v1/baseService/status")
    public Status status() {
        return new Status(statusCode, statusText);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api-v1/baseService/evalFile", method = RequestMethod.POST)
    public BulkEvalData evalFile(@RequestBody metaFile inputFile) {
        final long requestID = fileCounter.incrementAndGet();
        final String fileID = "sourceFile"+requestID+"."+getFileTypeFromName(inputFile.fileTitle());

        try(FileWriter fileWriter = new FileWriter("src/main/resources/Temp/"+fileID)) {
            fileWriter.write(inputFile.fileContents());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File tempFile = new File("src/main/resources/Temp/"+fileID);
        Formatter formatter = new Formatter(tempFile);
        //formatter.getOptionsHandler().setBraceStyleOption(BraceStyleOptions.ALLMAN);

        if (inputFile.settings().length > 0) {
            if (inputFile.settings()[0].equals("true")) {
                formatter.getOptionsHandler().applyOptionList(this.EvalDynamicSettings(formatter.getOrigional()));
            } else {
                formatter.getOptionsHandler().applyOptionList(inputFile.settings());
            }
        }

        EvaluateDifference eval = new EvaluateDifference(formatter.getOrigional(), formatter.getFormated());
        final SegmentMeta[] segs = eval.getDifferences();
        System.out.println("Found " + segs.length + " Formatting Errors:\n");

        SegmentLiteral[] literalSegs = new SegmentLiteral[segs.length];
        int errorCharCount = 0;
        int literalSegsCounter = 0;
        for(SegmentMeta seg : segs){
            int[] data = seg.getSegmentData();
            String preStr = (formatter.getOrigional()+"|").substring(data[0], data[1]+1).replace("\n", "/n").replace("\t", "/t").replace(" ", "_").replace("|", "");
            String postStr = (formatter.getFormated()+"|").substring(data[2], data[3]+1).replace("\n", "/n").replace("\t", "/t").replace(" ", "_").replace("|", "");
            StringBuilder errorType = new StringBuilder();
            if (preStr.contains("{") || preStr.contains("}")) {
                errorType.append("B");
            } if (preStr.contains("/n")) {
                errorType.append("N");
            } if (preStr.contains("/t")) {
                errorType.append("T");
            } if (preStr.contains("_")) {
                errorType.append("S");
            }
            System.out.println(errorType);
            literalSegs[literalSegsCounter] = new SegmentLiteral(preStr, postStr, errorType.toString());
            errorCharCount+=(data[1] - data[0]+1);
            literalSegsCounter++;
//            //Print out the mismatches for debugging reasons
//            System.out.println("Mismatch: " + (formatter.getOrigional()+"|").substring(data[0], data[1]+1).replace("\n", "&n").replace("\t", "&t").replace(" ", "_").replace("|", ""));
//            System.out.println("        : " + (formatter.getFormated()+"|").substring(data[2], data[3]+1).replace("\n", "&n").replace("\t", "&t").replace(" ", "_").replace("|", ""));
//            System.out.println(seg+"\n");
        }
        final long etcr = Math.round(((errorCharCount * 1.0) / formatter.getOrigional().length()) * 100);

        Pattern comPat = Pattern.compile("//.*|(?s)/\\*.*?\\*/"); //<- Somehow works on parsed java but not native java?
        Matcher matcher = comPat.matcher(formatter.getOrigional());
        int totalCommentCount = 0;
        while(matcher.find())
            totalCommentCount++;

        tempFile.delete();
        return new BulkEvalData(requestID, inputFile.fileTitle(), segs.length, etcr, totalCommentCount, segs, literalSegs);
    }

    private String getFileTypeFromName(final String fileName) {
        final String[] fileTitleParts = fileName.split("\\.");
        return fileTitleParts[fileTitleParts.length-1];
    }

    //Temporary this will be offloaded to it own class when fully implemented
    // Currently evaluating first occurrence.
    //TODO: Fix eval on no file or small file causing backend crash
    private String[] EvalDynamicSettings(String str) {
        ArrayList<String> options = new ArrayList<>();

        String newStr = "";
        String newStr2 = "";
        if (str.contains("{")) {
            newStr = str.split("\\{")[1];
            newStr2 = str.split("\\{")[0];
        }

        //Evaluate first indent style and add settings
        if (newStr.length() > 1 && newStr.charAt(1) == '\t') {
            options.add("2ForceTab");
        } else {
            options.add("2Spaces");
        }

        //Evaluate first bracket style and add setting
        if (newStr2.charAt(newStr2.length()-1) == '\n'){
            options.add("3Allman");
        } else {
            options.add("3Java");
        }

        return options.toArray(new String[0]);
    }
}
