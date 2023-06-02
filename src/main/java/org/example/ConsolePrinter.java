package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsolePrinter {

    public static void printToConsole(String filePath, int pageLength) throws IOException {

        var file = new File(FileReader.readFile(filePath), pageLength);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        CsvPrinter csvPrinter = new CsvPrinter();

        String input = "";
        int currentPage = 1;
        csvPrinter.printCsv(file, currentPage);

        while (!input.equals("E")) {
            input = br.readLine();

            switch (input) {
                case "F" -> currentPage = 1;
                case "P" -> currentPage = (currentPage <= 1) ? 1 : currentPage - 1;
                case "N" -> currentPage = (currentPage == file.getPages()) ? file.getPages() : currentPage + 1;
                case "L" -> currentPage = file.getPages();
                case "J" -> {
                    try {
                        currentPage = Integer.parseInt(br.readLine());
                    } catch (RuntimeException e) {
                        System.out.println("Must be a number between 1 and " + file.getPages());
                        continue;
                    }
                }
                default -> {
                    System.out.println("No valid input");
                    continue;
                }
            }

            csvPrinter.printCsv(file, currentPage);
        }
    }
}
