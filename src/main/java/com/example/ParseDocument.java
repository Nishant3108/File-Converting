package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;

public class ParseDocument {
    /*
     * This method parses an HTML file so it will be easier
     * to extract the desired elements
     * 
     */
    public Element parseHtmlDocument() {
        try {
            // Load the HTML file
            File downloadedFile = new File("C:/JAVA_PROGRAMS/FinalProject/obfuscated_course_report.html");
            // Parse the document so the code can easily pull out the necessary elements
            Document parsedDocument = Jsoup.parse(downloadedFile, "UTF-8");
            // Find the tables in the document
            Elements HtmlTables = parsedDocument.select("table");

            // Make sure the document has a second table and avoid the exception error
            try {
                Element secondTable = HtmlTables.get(1);
                // Return the parsed table
                return secondTable;
                // Catch the exception error if the document
                // does not have a second table
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error: The document does not contain a second table.");
                return null;
            }
            // Catch the exception error if the file cannot be read
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;

        }
    }
}
