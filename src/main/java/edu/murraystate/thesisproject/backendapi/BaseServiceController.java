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

        formatter.getOptionsHandler().applyOptionList(inputFile.settings());

        EvaluateDifference eval = new EvaluateDifference(formatter.getOrigional(), formatter.getFormated());
        final SegmentMeta[] segs = eval.getDifferences();
        System.out.println("Found " + segs.length + " Formatting Errors:\n");

        //TODO: This should be the total original char count divided by the total original issued char count
        // This is a temp number which needs fixed
        final long etcr = Math.round(((segs.length * 1.0) / inputFile.fileContents().length()) * 100);
        SegmentLiteral[] literalSegs = new SegmentLiteral[segs.length];
        int literalSegsCounter = 0;
        for(SegmentMeta seg : segs){
            int[] data = seg.getSegmentData();
            literalSegs[literalSegsCounter] = new SegmentLiteral((formatter.getOrigional()+"|").substring(data[0], data[1]+1).replace("\n", "/n").replace("\t", "/t").replace(" ", "_").replace("|", ""),
                    (formatter.getFormated()+"|").substring(data[2], data[3]+1).replace("\n", "/n").replace("\t", "/t").replace(" ", "_").replace("|", ""),
                    -1);
            literalSegsCounter++;
//            //Print out the mismatches for debugging reasons
//            System.out.println("Mismatch: " + (formatter.getOrigional()+"|").substring(data[0], data[1]+1).replace("\n", "&n").replace("\t", "&t").replace(" ", "_").replace("|", ""));
//            System.out.println("        : " + (formatter.getFormated()+"|").substring(data[2], data[3]+1).replace("\n", "&n").replace("\t", "&t").replace(" ", "_").replace("|", ""));
//            System.out.println(seg+"\n");
        }

        Pattern comPat = Pattern.compile("//.*|(?s)/\\*.*?\\*/"); //<- Somehow works on parsed java but not native java?
        Matcher matcher = comPat.matcher(formatter.getOrigional());
        int totalCommentCount = 0;
        while(matcher.find())
            totalCommentCount++;


        tempFile.delete();
        return new BulkEvalData(requestID, inputFile.fileTitle(), segs.length, etcr, totalCommentCount, segs, literalSegs);
    }

    private String getFileTypeFromName(final String fileName){
        final String[] fileTitleParts = fileName.split("\\.");
        return fileTitleParts[fileTitleParts.length-1];
    }
}
