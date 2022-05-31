package etapa2.main.src;

// imports for reading the csv file, storing amounts and user input
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// import for the general reading configurations of the file
import etapa2.CsvConfig;

public class BuscaPeloCodigoDeBarras {

    // method to ask for the bar code
    private String getBarCode() {
        Scanner input = new Scanner(System.in);
        String barCode;

        System.out.print("Digite o código de barras: ");
        barCode = input.nextLine();

        return barCode;
    }

    // method that scans the csv file for the medicine acording to its bar code
    public void lookForMedicine() {
        String barCode = getBarCode();

        final String FILE_PATH = CsvConfig.FILE_PATH;
        File csvfile = new File(FILE_PATH);

        String fileLine;
        String regex = CsvConfig.COLUMN_SEPARATOR;
        String[] fileLineContent;
        boolean foundMedicine = false;
        ArrayList<Double> pmcAmount = new ArrayList<>();

        try (Scanner reader = new Scanner(csvfile)) {
            // skip header when scanning
            reader.nextLine();

            while (reader.hasNext()) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(regex);

                // since pmc values are on column 23, I need the product to have at least this
                // many columns
                if (fileLineContent.length > 23) {

                    // all numbers on the csv file were converted to String by using ' character
                    // before, and they are now being removed
                    for (int i = 3; i <= 7; i++) {
                        fileLineContent[i] = fileLineContent[i].substring(1);
                    }

                    if (fileLineContent[5].equals(barCode)) {
                        // converting the String containing the [PMC 0%] value to a number
                        // the replace function is there because in the csv file numbers are represented
                        // by a comma (,), but in Java by a period (.)
                        double pmc = Double.parseDouble(fileLineContent[23].replace(",", "."));
                        pmcAmount.add(pmc);
                        foundMedicine = true;
                    }
                }
            }

            if (foundMedicine) {
                medicineInfo(extremePmc(pmcAmount), barCode);
            } else {
                System.out.printf("Infelizmente, não foram encontrados medicamentos por \"%s\".%n", barCode);
                handleUserResponse(lookAgain());
            }

        } catch (Exception e) {
            System.out.println("Houve um erro na leitura do arquivo. Tente novamente.");
        }
    }

    // method to sort the highest and lowest pmc values
    private double[] extremePmc(ArrayList<Double> pmcAmount) {
        double[] extremePmc = new double[2];
        extremePmc[0] = Double.MAX_VALUE;

        for (double pmc : pmcAmount) {
            if (pmc >= extremePmc[1]) {
                extremePmc[1] = pmc;
            }
            if (pmc <= extremePmc[0]) {
                extremePmc[0] = pmc;
            }
        }

        return extremePmc;
    }

    // method that displays more information on the medicine
    private void medicineInfo(double[] extremePmc, String barCode) {
        double highestPmc = extremePmc[1];
        double lowestPmc = extremePmc[0];
        double pmcDifference = highestPmc - lowestPmc;

        System.out.printf("%n Dados dos medicamentos encontrado por \"%s\" %n", barCode);
        System.out.printf("| PMC mais alto: %.2f %n", highestPmc);
        System.out.printf("| PMC mais baixo: %.2f %n", lowestPmc);
        System.out.printf("| Diferença entre PMC's: %.2f %n", pmcDifference);
    }

    // method to analyse user response and determin if program should run again
    private boolean handleUserResponse(String userResponse) {
        userResponse = userResponse.toLowerCase();
        switch (userResponse) {
            case "sim":
                return true;
            case "nao":
                return false;
            default:
                System.out.println("Valor inválido. Tente novamente. \n");
                return handleUserResponse(lookAgain());
        }
    }

    // method to ask the user if he wishes to try another search
    private String lookAgain() {
        Scanner input = new Scanner(System.in);
        String userResponse;

        System.out.print("Buscar novamente? (Sim / Nao)\t");
        userResponse = input.nextLine();

        return userResponse;
    }
}
