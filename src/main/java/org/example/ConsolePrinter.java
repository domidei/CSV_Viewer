package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsolePrinter {

    private static final int HEADER_LENGTH = 1;

    public static void printToConsole(String filePath, int outputLength) throws IOException {

        var file = new File(FileReader.readFile(filePath), outputLength);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        CsvPrinter csvPrinter = new CsvPrinter();

        String input = "";
        var currentPage = 1;
        int startElement = 0;
        while (!input.equals("E")) {
            csvPrinter.printCsv(file, outputLength, startElement);

            System.out.println("Page " + currentPage + " of " + file.getPages());
            System.out.println("F)irst page, P)revious page, N)ext page, L)ast page, J)ump to page, E)xit");
            input = br.readLine();

            if (input.equals("F"))
                startElement = 0;

            if (input.equals("P"))
                startElement = (startElement <= outputLength) ? 0 : startElement - outputLength;

            if (input.equals("N"))
                startElement = (startElement + outputLength + outputLength >= file.getContent().size() - HEADER_LENGTH) ? file.getContent().size() - HEADER_LENGTH - outputLength : startElement + outputLength;

            if (input.equals("L"))
                startElement = file.getContent().size() - 1 - outputLength;
        }
    }
}
