package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {

    private static final String COMMA_DELIMITER = ";";

    /*    public static List<List<String>> readFile(String filePath) {
            List<List<String>> records = new ArrayList<>();
            try (CSVReader csvReader = new CSVReader(new java.io.FileReader(filePath))) {
                String[] values;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return records;
        }*/
    public static List<List<String>> readFile(String filePath) {
        List<List<String>> fileArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                fileArray.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileArray;

    }

}
