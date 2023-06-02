package org.example;

import java.util.Comparator;
import java.util.List;

public class FileSorter {

    public static File sortFile(File file, String columnName) {

        var header = file.getHeader().subList(1, file.getHeader().size());
        if (!header.contains(columnName)) {
            System.out.println("Column is not valid");
        }

        List<List<String>> content = file.getContent().subList(1, file.getContent().size());

        int columnToSortBy = header.indexOf(columnName);
        content.sort(new ColumnComparator(columnToSortBy));
        content.add(0, header);
        return new File(content, file.getPageLength());
    }


    static class ColumnComparator implements Comparator<List<String>> {
        private final int columnToSortBy;

        ColumnComparator(int columnToSortBy) {
            this.columnToSortBy = columnToSortBy;
        }

        @Override
        public int compare(List<String> row1, List<String> row2) {
            String cell1 = row1.get(columnToSortBy);
            String cell2 = row2.get(columnToSortBy);
            return cell1.compareTo(cell2);
        }
    }
}

