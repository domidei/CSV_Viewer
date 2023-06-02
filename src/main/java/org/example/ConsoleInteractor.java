package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInteractor {

    public static void start(String filePath, int pageLength) throws IOException {

        var file = new File(FileReader.readFile(filePath), pageLength);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String input = "";
        int currentPage = 1;
        CsvPrinter.printCsv(file, currentPage);

        while (!input.equals("E")) {
            input = br.readLine();

            switch (input) {
                case "F" -> currentPage = 1;
                case "P" -> currentPage = (currentPage <= 1) ? 1 : currentPage - 1;
                case "N" -> currentPage = (currentPage == file.getPageCount()) ? file.getPageCount() : currentPage + 1;
                case "L" -> currentPage = file.getPageCount();
                case "J" -> currentPage = jumpToPage(file, br, currentPage);
                case "S" -> file = sort(file, br);
                default -> System.out.println("No valid input");
            }

            CsvPrinter.printCsv(file, currentPage);
        }
    }

    private static File sort(File file, BufferedReader br) throws IOException {
        var sortInput = br.readLine();
        try {
            file = FileSorter.sortFile(file, sortInput);
        } catch (RuntimeException e) {
            System.out.println("Try again:");
        }
        return file;
    }

    private static int jumpToPage(File file, BufferedReader br, int currentPage) throws IOException {
        try {
            var pageInput = Integer.parseInt(br.readLine());
            if (!(0 < pageInput && pageInput <= file.getPageCount())) {
                throw new RuntimeException();
            } else {
                currentPage = pageInput;
            }
        } catch (RuntimeException e) {
            System.out.println("Must be a number between 1 and " + file.getPageCount());
            System.out.println("Try again:");
        }
        return currentPage;
    }
}

