package Etapa2.Testes;

import java.io.File;
import java.util.Scanner;

public class ReadCSV {
    public static void main(String[] args) {
        final String FILE_PATH = "D:/Programming/GitHub/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File csvFile = new File(FILE_PATH);

        int lineNumber = 1;
        String regex = ";";
        String fileLine = "";
        String[] fileLineContent;

        try (Scanner reader = new Scanner(csvFile)) {
            while (reader.hasNext() && lineNumber <= 5) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(regex);

                System.out.println("\nLine " + lineNumber + ": " + fileLine);
                for (int i = 0; i < fileLineContent.length; i++) {
                    // all numbers on the csv file were converted to String by using ' character
                    // before, and they are now being removed
                    // if line contains a number and is not the header
                    if (((i >= 3) && (i <= 7)) && (lineNumber != 1)) {
                        fileLineContent[i] = fileLineContent[i].substring(1);
                    }
                    System.out.println("Column " + i + ": " + fileLineContent[i]);
                }

                lineNumber++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
