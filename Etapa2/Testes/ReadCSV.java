package etapa2.testes;

import java.io.File;
import java.util.Scanner;

import etapa2.CsvConfig;

public class ReadCSV {
    public static void main(String[] args) {
        final String FILE_PATH = CsvConfig.FILE_PATH;
        File csvFile = new File(FILE_PATH);

        int lineNumber = 1;
        String fileLine;
        final String COLUMN_SEPARATOR = CsvConfig.COLUMN_SEPARATOR;
        String[] fileLineContent;

        try (Scanner reader = new Scanner(csvFile)) {
            while ((reader.hasNext()) && (lineNumber < 3)) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(COLUMN_SEPARATOR);

                System.out.printf("%n Line %d: %s %n", lineNumber, fileLine);
                for (int i = 0; i < fileLineContent.length; i++) {
                    // all numbers on the csv file were converted to String by using ' character
                    // before, and they are now being removed
                    // if line contains a number and is not the header
                    if (((i >= 3) && (i <= 7)) && (lineNumber != 1)) {
                        fileLineContent[i] = fileLineContent[i].substring(1);
                    }
                    System.out.printf("Column %d: %s %n", i, fileLineContent[i]);
                }

                lineNumber++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
