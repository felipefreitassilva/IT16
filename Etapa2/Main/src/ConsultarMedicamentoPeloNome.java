package etapa2.main.src;

// imports for reading the csv file and user input
import java.io.File;
import java.util.Scanner;

// import for the general reading configurations of the file
import etapa2.CsvConfig;

public class ConsultarMedicamentoPeloNome {

    // method to ask for the name of the medicine
    private String medicineName() {
        Scanner input = new Scanner(System.in);
        String medicineName;

        System.out.print("Digite o nome do medicamento: ");
        medicineName = input.nextLine();

        return medicineName;
    }

    // method that scans the csv file for the medicine acording to its name
    public void lookForMedicine() {
        String medicineName = medicineName();

        final String FILE_PATH = CsvConfig.FILE_PATH;
        File csvfile = new File(FILE_PATH);

        try (Scanner reader = new Scanner(csvfile)) {
            String fileLine;
            String columnSeparator = CsvConfig.COLUMN_SEPARATOR;
            String[] fileLineContent;
            int medicineNumber = 1;
            boolean foundMedicine = false;

            // skip header when scanning
            reader.nextLine();

            while (reader.hasNext()) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(columnSeparator);

                // if the file line contains the medicine and it was produced in 2020
                if ((fileLineContent[0].contains(medicineName.toUpperCase()))
                        && (fileLineContent[38].equals("Sim"))) {
                    medicineInfo(medicineNumber, medicineName, fileLineContent);
                    foundMedicine = true;
                    medicineNumber++;
                }
            }

            if (!foundMedicine) {
                System.out.printf("Infelizmente, não foram encontrados medicamentos por \"%s\".%n", medicineName);
            }

        } catch (Exception e) {
            System.out.println("Houve um erro na leitura do arquivo. Tente novamente.");
        }
    }

    // method that displays more information on the medicine
    public void medicineInfo(int medicineNumber, String medicineName, String[] fileLineContent) {
        System.out.printf("%n Dados do %dº medicamento encontrado por \"%s\" %n", medicineNumber, medicineName);
        System.out.println("| Nome: " + fileLineContent[0]);
        System.out.println("| Produto: " + fileLineContent[8]);
        System.out.println("| Apresentação: " + fileLineContent[9]);
        System.out.println("| Valor PF Sem Impostos: R$ " + fileLineContent[13]);
    }
}
