package Etapa2.Main.src;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class BuscaPeloCodigoDeBarras {

    // method to ask for the bar code
    private String getBarCode() {
        String barCode;
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código de barras: ");
        barCode = input.nextLine();

        return barCode;
    }

    // method that scans the csv file for the medicine acording to its name
    public void lookForMedicine() {
        String barCode = getBarCode();

        final String FILE_PATH = "Etapa2\\TA_PRECO_MEDICAMENTO.csv";
        File csvfile = new File(FILE_PATH);

        String fileLine;
        String regex = ";";
        String[] fileLineContent;
        boolean foundMedicine = false;
        ArrayList<Double> pmcAmount = new ArrayList<Double>();

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
                        // converting the String containing the pmc 0% value to a number
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
                lookAgain();
            }

        } catch (Exception e) {
            System.out.println("Houve um erro na leitura do arquivo. Tente novamente." + e);
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

    // method to ask the user if he wished to try another search
    private void lookAgain() {
        Scanner input = new Scanner(System.in);
        String userResponse;
        System.out.println("Buscar novamente? (Sim / Nao)");
        userResponse = input.nextLine();
        if (handleUserResponse(userResponse)) {
            lookForMedicine();
        }
    }

    // method to analyse user response and determin if program should run again
    private boolean handleUserResponse(String userResponse) {
        userResponse = userResponse.toUpperCase();
        switch (userResponse) {
            case "SIM":
                return true;
            case "NAO":
                return false;
            default:
                System.out.println("Valor inválido. Tente novamente.");
                return false;
        }
    }
}
