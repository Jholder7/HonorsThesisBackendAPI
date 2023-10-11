package edu.murraystate.thesisproject.backendapi.core;

import edu.murraystate.thesisproject.backendapi.core.formatoptions.FormatOptionHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TODO: Document
public class Formatter {
    private final String originalStr;
    private String formattedStr;
    private FormatOptionHandler formatOptions;

    //TODO: Document
    public Formatter(final File file) {
        this.formatOptions = new FormatOptionHandler();
        try {
            StringBuilder str1 = new StringBuilder();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line;
            while ((line = buffer.readLine()) != null) {
                str1.append(line).append("\n");
            }
            originalStr = str1.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: Document
    public FormatOptionHandler getOptionsHandler(){
        return this.formatOptions;
    }

    //TODO: Document
    public String getOrigional() {
        return this.originalStr;
    }

    //TODO: Document
    //TODO: Add Error Handling
    public String getFormated() {
        //Memoize formatted string, so it can act like a getter efficiently
        if (this.formattedStr != null) {return this.formattedStr;}

        StringBuilder formattedString = new StringBuilder();
        try {
            //Rewrite the input file to a temp file, so we can edit it without effecting the original
            FileWriter fileWriter = new FileWriter("src/main/resources/Temp/TempScanInfile.java");
            fileWriter.write(this.originalStr);
            fileWriter.close();

            //Run the file through Astyle to get the new formatted file
            List<String> cmds = new ArrayList<>();
            cmds.add("src/main/resources/astyle.exe");
            cmds.add("src/main/resources/Temp/TempScanInfile.java");
            cmds.add("--suffix=none");
            cmds.addAll(formatOptions.getOptionSet());
            ProcessBuilder proc = new ProcessBuilder(cmds);
            proc.start().waitFor();

            //Scan back in the formatted file, so we can pass it around as a string
            BufferedReader stdInput = new BufferedReader(new FileReader("src/main/resources/Temp/TempScanInfile.java"));
            String line;
            while ((line = stdInput.readLine()) != null) {
                formattedString.append(line).append("\n");
            }
            stdInput.close();

//            File file = new File("src/main/resources/Temp/TempScanInfile.java");
//            file.delete();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.formattedStr = formattedString.toString();
        return formattedString.toString();
    }
}
