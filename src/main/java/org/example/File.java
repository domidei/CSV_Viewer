package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class File {

    private List<String> header;
    private List<List<List<String>>> bodyParts;
    private int pages;
    private int pageLength;

    private List<Integer> columnWidths;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<List<List<String>>> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<List<List<String>>> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public File(List<List<String>> content, int pageLength) {
        var numberedContent = addNumbers(content);
        this.pages = (numberedContent.size() % pageLength == 0) ? numberedContent.size() / pageLength : numberedContent.size() / pageLength + 1;
        this.header = numberedContent.get(0);
        this.bodyParts = splitList(numberedContent.subList(1, numberedContent.size()), pages);
        this.pageLength = pageLength;
        this.columnWidths = getColumnWidths(numberedContent);
    }

    public <T> List<List<T>> splitList(List<T> list, final int batchSize) {
        List<List<T>> parts = new ArrayList<>();
        final int listSize = list.size();
        List<T> batch;
        for (int i = 0; i < listSize; i += batchSize) {
            batch = list.subList(i, Math.min(listSize, i + batchSize));
            parts.add(batch);
        }
        return parts;
    }

    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(List<Integer> columnWidths) {
        this.columnWidths = columnWidths;
    }

    private static List<Integer> getColumnWidths(List<List<String>> content) {
        List<Integer> columnWidths = new ArrayList<>();
        for (int i = 0; i < content.get(0).size(); i++) {
            int finalI = i;
            columnWidths.add(content.stream().map(element -> element.get(finalI)).max(Comparator.comparingInt(String::length)).orElseThrow().length());
        }

        return columnWidths;
    }

    private List<List<String>> addNumbers(List<List<String>> content) {
        List<List<String>> newContent = new ArrayList<>();

        var header = new ArrayList<String>();
        header.add("No.");
        for (String element : content.get(0)) {
            header.add(element);
        }
        newContent.add(header);

        for (int i = 1; i < content.size(); i++) {
            var line = new ArrayList<String>();
            line.add(String.format("%d.", i));
            for (String element : content.get(i)) {
                line.add(element);
            }
            newContent.add(line);
        }

        return newContent;
    }
}
