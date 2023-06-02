package org.example;

import java.io.IOException;

public class CsvViewer {
    public static void main(String[] args) throws IOException {

        var filePath = args[0];
        var pageLength = Integer.parseInt(args[1]);
        ConsoleInteractor.start(filePath, pageLength);

    }
}