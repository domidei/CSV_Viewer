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
        printNavigationOutput(currentPage, file.getPages());

        while (!input.equals("E")) {
            input = br.readLine();

            switch (input) {
                case "F" -> currentPage = 1;
                case "P" -> currentPage = (currentPage <= 1) ? 1 : currentPage - 1;
                case "N" -> currentPage = (currentPage == file.getPages()) ? file.getPages() : currentPage + 1;
                case "L" -> currentPage = file.getPages();
                case "J" -> {
                    try {
                        var pageInput = Integer.parseInt(br.readLine());
                        if (!(0 < pageInput && pageInput <= pageLength)) {
                            throw new RuntimeException();
                        } else {
                            currentPage = pageInput;
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Must be a number between 1 and " + file.getPages());
                        System.out.println("Try again:");
                    }
                }
                case "S" -> {
                    var sortInput = br.readLine();
                    try {
                        file = FileSorter.sortFile(file, sortInput);
                    } catch (RuntimeException e) {
                        System.out.println("Must be a valid column");
                        System.out.println("Try again:");
                    }

                }
                default -> {
                    System.out.println("No valid input");
                    continue;
                }
            }

            csvPrinter.printCsv(file, currentPage);
            printNavigationOutput(currentPage, file.getPages());
        }
    }

    private static void printNavigationOutput(int currentPage, int pages) {
        System.out.println("Page " + currentPage + " of " + pages);
        System.out.println("F)irst page, P)revious page, N)ext page, L)ast page, J)ump to page, S)ort, E)xit");
    }
}
