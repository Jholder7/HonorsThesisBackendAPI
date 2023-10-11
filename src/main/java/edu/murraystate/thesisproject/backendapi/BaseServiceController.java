package edu.murraystate.thesisproject.backendapi;

import edu.murraystate.thesisproject.backendapi.core.EvaluateDifference;
import edu.murraystate.thesisproject.backendapi.core.Formatter;
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
        System.out.println(inputFile.fileContents());
        final long requestID = fileCounter.incrementAndGet();
        final String fileID = "sourceFile"+requestID+"."+getFileTypeFromName(inputFile.fileTitle());

        try(FileWriter fileWriter = new FileWriter("src/main/resources/Temp/"+fileID)) {
            fileWriter.write(inputFile.fileContents());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File tempFile = new File("src/main/resources/Temp/"+fileID);
        Formatter formatter = new Formatter(tempFile);
        tempFile.delete();

        EvaluateDifference eval = new EvaluateDifference(inputFile.fileContents(), formatter.getFormated());
        final SegmentMeta[] segs = eval.getDifferences();

        //TODO: This should be the total original char count divided by the total original issued char count
        // This is a temp number which needs fixed
        final long etcr = Math.round(((segs.length * 1.0) / inputFile.fileContents().length()) * 100);

        return new BulkEvalData(requestID, inputFile.fileTitle(), segs, segs.length, etcr, -1);
    }

    private String getFileTypeFromName(final String fileName){
        final String[] fileTitleParts = fileName.split("\\.");
        return fileTitleParts[fileTitleParts.length-1];
    }

//    @GetMapping("/greeting")
//    public BackendService greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new BackendService(counter.incrementAndGet(), String.format(template, name));
//    }
}