package com.example;

import org.jsoup.nodes.Element;

public class Main {
    public static void main(String[] args) {

        ParseDocument parseDocument = new ParseDocument();
        Element secondTable = parseDocument.parseHtmlDocument();
        CsvWriter.writeCSVFile(secondTable);

    }
}