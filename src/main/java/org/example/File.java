package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class File {

    private List<List<String>> content;
    private List<String> header;
    private List<List<List<String>>> pages;
    private int pageCount;
    private int pageLength;

    private List<Integer> columnWidths;

    public File(List<List<String>> content, int pageLength) {
        this.content = content;
        var numberedContent = addNumbers(content);
        this.pageCount = (numberedContent.size() % pageLength == 0) ? numberedContent.size() / pageLength : numberedContent.size() / pageLength + 1;
        this.header = numberedContent.get(0);
        this.pages = splitList(numberedContent.subList(1, numberedContent.size()), pageLength);
        this.pageLength = pageLength;
        this.columnWidths = getColumnWidths(numberedContent);
    }

    public List<String> getHeader() {
        return header;
    }

    public List<List<List<String>>> getPages() {
        return pages;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageLength() {
        return pageLength;
    }

    public List<List<String>> getContent() {
        return content;
    }


    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    private List<Integer> getColumnWidths(List<List<String>> content) {
        List<Integer> columnWidths = new ArrayList<>();
        for (int i = 0; i < content.get(0).size(); i++) {
            int finalI = i;
            columnWidths.add(content.stream().map(element -> element.get(finalI)).max(Comparator.comparingInt(String::length)).orElseThrow().length());
        }

        return columnWidths;
    }

    private  <T> List<List<T>> splitList(List<T> list, final int batchSize) {
        List<List<T>> parts = new ArrayList<>();
        final int listSize = list.size();
        List<T> batch;
        for (int i = 0; i < listSize; i += batchSize) {
            batch = list.subList(i, Math.min(listSize, i + batchSize));
            parts.add(batch);
        }
        return parts;
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
