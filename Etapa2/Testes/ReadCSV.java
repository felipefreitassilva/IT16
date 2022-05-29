package Etapa2.Testes;

import java.io.File;
import java.util.Scanner;

public class ReadCSV {
    public static void main(String[] args) {
        // final String FILE_PATH =
        // "https://dados.anvisa.gov.br/dados/TA_PRECO_MEDICAMENTO.csv";
        final String FILE_PATH = "D:/Programming/GitHub/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File csvFile = new File(FILE_PATH);

        try {
            int lineNumber = 1;
            String regex = ",";
            String fileLine = "";
            String[] fileLineContent;

            try (Scanner reader = new Scanner(csvFile)) {
                // while (reader.hasNext()) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(regex);

                System.out.println("\nLine " + lineNumber + ": " + fileLine);
                for (int i = 0; i < fileLineContent.length; i++) {
                    System.out.println("Column " + i + ": " + fileLineContent[i]);
                }

                lineNumber++;
                // }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
