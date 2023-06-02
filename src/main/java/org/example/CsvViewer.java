package org.example;

import java.io.IOException;

public class CsvViewer {
    public static void main(String[] args) {

        var filePath = args[0];
        var pageLength = Integer.parseInt(args[1]);
        try {
            ConsoleInteractor.start(filePath, pageLength);
        } catch (RuntimeException | IOException e) {
            System.out.println("Wrong filepath or invalid page length");
            throw new RuntimeException(e);
        }

    }
}