package org.example;

import java.util.ArrayList;
import java.util.List;

public class CsvPrinter {

    public void printCsv(File file, int page) {

        printHeader(file);
        printBodyPart(file, page - 1);
        printInteractionText(file, page);

    }

    private void printInteractionText(File file, int page) {
        System.out.println("Page " + page + " of " + file.getPages());
        System.out.println("F)irst page, P)revious page, N)ext page, L)ast page, J)ump to page, E)xit");
    }

    private void printHeader(File file) {
        var header = file.getHeader();
        List<Integer> columnSpaces = getColumnSpaces(header, file.getColumnWidths());

        StringBuilder headerPrint = new StringBuilder();
        StringBuilder headerLine = new StringBuilder();

        for (int i = 0; i < header.size(); i++) {
            headerPrint.append(header.get(i)).append(" ".repeat(columnSpaces.get(i))).append("|");
            headerLine.append("-".repeat(file.getColumnWidths().get(i))).append("+");
        }

        System.out.println(headerPrint);
        System.out.println(headerLine);
    }


    private void printBodyPart(File file, int page) {

        var bodyPart = file.getBodyParts().get(page);
        StringBuilder linePrint = new StringBuilder();

        for (List<String> strings : bodyPart) {
            for (int j = 0; j < strings.size(); j++) {

                List<Integer> columnSpaces = getColumnSpaces(strings, file.getColumnWidths());
                linePrint.append(strings.get(j)).append(" ".repeat(columnSpaces.get(j))).append("|");
            }
            System.out.println(linePrint);
            linePrint = new StringBuilder();
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
