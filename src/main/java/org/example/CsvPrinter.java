package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CsvPrinter {

    public void printCsv(File file, int length, int page) {

        List<Integer> columnWidths = getColumnWidths(file.getContent());

        var header = file.getContent().get(0);
        var body = file.getContent().subList(1, file.getContent().size());
        var part = body.subList(page, page + length);


        printHeader(header, columnWidths);
        printPart(part, columnWidths);
    }

    private static List<Integer> getColumnWidths(List<List<String>> content) {
        List<Integer> columnWidths = new ArrayList<>();
        for (int i = 0; i < content.get(0).size(); i++) {
            int finalI = i;
            columnWidths.add(content.stream().map(element -> element.get(finalI)).max(Comparator.comparingInt(String::length)).get().length());
        }

        return columnWidths;
    }

    private void printHeader(List<String> header, List<Integer> columnWidths) {
        List<Integer> columnSpaces = getColumnSpaces(header, columnWidths);

        String headerPrint = "";
        String headerLine = "";

        for (int i = 0; i < header.size(); i++) {
            headerPrint = headerPrint + header.get(i) + " ".repeat(columnSpaces.get(i)) + "|";
            headerLine = headerLine + "-".repeat(columnWidths.get(i)) + "+";
        }

        System.out.println(headerPrint);
        System.out.println(headerLine);
    }


    private void printPart(List<List<String>> body, List<Integer> columnWidths) {

        String linePrint = "";

        for (int i = 0; i < body.size(); i++) {
            for (int j = 0; j < body.get(i).size(); j++) {

                List<Integer> columnSpaces = getColumnSpaces(body.get(i), columnWidths);
                linePrint = linePrint + body.get(i).get(j) + " ".repeat(columnSpaces.get(j)) + "|";
            }
            System.out.println(linePrint);
            linePrint = "";
        }
    }

    private static List<Integer> getColumnSpaces(List<String> header, List<Integer> columnWidths) {
        List<Integer> columnSpaces = new ArrayList<>();

        for (int i = 0; i < header.size(); i++) {
            columnSpaces.add(columnWidths.get(i) - header.get(i).length());
        }
        return columnSpaces;
    }
}
