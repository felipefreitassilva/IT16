package Etapa2.Testes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSV {
    public static void main(String[] args) {
        final String FILE_PATH = "C:/Users/felip/Desktop/log/PUCRS/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File CSVFile = new File(FILE_PATH);

        try {
            String fileLines = new String();

            try (Scanner reader = new Scanner(CSVFile)) {
                while (reader.hasNext()) {
                    fileLines = reader.nextLine();

                    System.out.println(fileLines);
                }
            }

        } catch (FileNotFoundException e) {
            
        }

    }
}
