package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.nodes.Element;

public class CsvWriter {
    /*
     * This method writes the contents of the parsed document
     * into CSV format
     */
    public static void writeCSVFile(Element secondTable, String outputFilePath) {
        try {
            // Create a new CSV file writer
            FileWriter newCSVDocument = new FileWriter(outputFilePath);
            try (CSVPrinter csvPrinter = new CSVPrinter(newCSVDocument, CSVFormat.DEFAULT)) {
                // Loop through each row of the second table
                // by selecting the tr (table row) elements
                for (Element row : secondTable.select("tr")) {

                    List<String> rowData = new ArrayList<>();
                    // Extract the text from each cell in the row
                    // selecting the th (table header) and td (table data) elements
                    for (Element cell : row.select("th, td")) {
                        String text = cell.text().trim();
                        // If the cell is not empty, add it to the row
                        if (!text.isEmpty()) {
                            rowData.add(text);
                        }
                    }
                    // Make sure the row is not empty before writing
                    if (!rowData.isEmpty()) {
                        csvPrinter.printRecord(rowData);
                        System.out.println(rowData);
                    }
                }
                // Ensure the CSV is written by clearing out
                // the buffer and then print the output location
                csvPrinter.flush();
                System.out.println("CSV file created at " + outputFilePath);
            } // Catch the exception error if the CSV file cannot be written
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
