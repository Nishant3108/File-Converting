package com.example;

import java.io.File;
import org.jsoup.nodes.Element;
import javafx.application.Application;

public class Main {
    /*
     * This method calls the methods in the ParseDocument and CsvWriter classes
     * to process the uploaded HTML file and write it to a CSV file
     */
    public String FileProcessor(File uploadedFile, File downloadFolder) {
        // Parse the uploaded HTML file
        ParseDocument parseDocument = new ParseDocument();
        Element secondTable = parseDocument.parseHtmlDocument(uploadedFile);
        // Ensure the second table is not null
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

            // Return a message saying it was successful
            // or that it was unsuccessful
            return "File processed and saved to: " + outputFilePath;
        } else {
            return "Error: Unable to parse the uploaded file.";
        }
    }

    /*
     * This method launches the application
     * 
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        Application.launch(UI.class, args);
    }

}