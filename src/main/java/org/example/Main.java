package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class Main implements Callable<Integer> {

    @Option(names = {"-t", "--text"}, description = "Path to the text file to summarize")
    private File textFile;

    @Parameters(index = "0", description = "Text to summarize", arity = "0..1")
    private String textInput;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String content;
        if (textFile != null) {
            content = new String(Files.readAllBytes(textFile.toPath()));
        } else if (textInput != null) {
            content = textInput;
        } else {
            System.out.println("No text provided for summarization.");
            return 1;
        }

        String summary = summarize(content);
        System.out.println("Summary:\n" + summary);
        return 0;
    }


    private String summarize(String text) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ollama", "run", "qwen2-0.5b", "--input", text);
        Process process = processBuilder.start();
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
}