package com.example;

import java.io.File;

import org.jsoup.nodes.Element;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        // Launch the JavaFX application
        Application.launch(UI.class, args);
    }

    public void FileProcessor(File uploadedFile, File downloadFolder) {
        // Parse the uploaded HTML file
        ParseDocument parseDocument = new ParseDocument();
        Element secondTable = parseDocument.parseHtmlDocument(uploadedFile);

        if (secondTable != null) {
            // Define the output CSV file path
            String outputFilePath = downloadFolder.getAbsolutePath() + "/outputDocument.csv";
            File outputFile = new File(outputFilePath);
            int counter = 1;

            // Check if the file already exists and modify the name if needed
            while (outputFile.exists()) {
                outputFilePath = downloadFolder.getAbsolutePath() + "/outputDocument(" + counter + ").csv";
                outputFile = new File(outputFilePath);
                counter++;
            }

            // Write the parsed data to the CSV file
            CsvWriter.writeCSVFile(secondTable, outputFilePath);
            System.out.println("File processed and saved to: " + outputFilePath);
        } else {
            System.err.println("Error: Unable to parse the uploaded file.");
        }
    }

}