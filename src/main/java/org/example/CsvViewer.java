package org.example;

import java.io.IOException;

public class CsvViewer {
    public static void main(String[] args) {

        //args[0]
        //args[1]
        try {
            ConsolePrinter.printToConsole("/Users/dode/Documents/LearningResources/CleanCodeSchulung/csvviewer/src/main/resources/csv-viewer.csv", 3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}