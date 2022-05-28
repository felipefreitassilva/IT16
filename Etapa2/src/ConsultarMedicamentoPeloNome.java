package Etapa2.src;

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsultarMedicamentoPeloNome {

    // main method that looks for the medicine according to an user input
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        lookForMedicine(medicineName(input));
    }

    // method to ask for the name of the medicine
    private static String medicineName(Scanner input) {

        String nomeMedicamento;

        System.out.print("Digite o nome do medicamento: ");
        nomeMedicamento = input.nextLine();

        return nomeMedicamento;
    }

    // method that scans the csv file for the medicine acording to its name
    private static void lookForMedicine(String medicineName) {

        final String FILE_PATH = "D:/Programming/GitHub/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File csvfile = new File(FILE_PATH);

        try {
            String fileLine;
            String regex = ";";
            String[] fileLineContent;
            int medicineNumber = 1;
            boolean foundMedicine = false;

            try (Scanner reader = new Scanner(csvfile)) {
                // skip header when scanning
                reader.nextLine();

                while (reader.hasNext()) {
                    fileLine = reader.nextLine();
                    fileLineContent = fileLine.split(regex);

                    // if the file line contains the medicine and it was produced in 2020
                    if ((fileLineContent[0].contains(medicineName.toUpperCase()))
                            && (fileLineContent[38].contains("Sim"))) {
                        medicineInfo(medicineNumber, fileLineContent, medicineName);
                        foundMedicine = true;
                        medicineNumber++;
                    }
                }

                if (!foundMedicine) {
                    System.out.printf("Infelizmente, não foram encontrados medicamentos por \"%s\".", medicineName);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // method that displays more information on the medicine
    private static void medicineInfo(int medicineNumber, String[] fileLineContent, String medicineName) {
        System.out.printf("%n Dados do %dº medicamento encontrado por \"%s\" %n", medicineNumber, medicineName);
        System.out.println("| Nome: " + fileLineContent[0]);
        System.out.println("| Produto: " + fileLineContent[8]);
        System.out.println("| Apresentação: " + fileLineContent[9]);
        System.out.println("| Valor PF Sem Impostos: R$ 6" + fileLineContent[13]);
    }
}
