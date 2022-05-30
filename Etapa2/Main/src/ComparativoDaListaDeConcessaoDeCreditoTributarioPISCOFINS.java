package Etapa2.Main.src;

import java.io.File;
import java.util.Scanner;

public class ComparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS {

    // method that scans the csv file for the medicine acording to its name
    public void compareCreditPercentuals() {

        final String FILE_PATH = "Etapa2\\TA_PRECO_MEDICAMENTO.csv";
        File csvfile = new File(FILE_PATH);

        int negativeAmount = 0;
        int neutralAmount = 0;
        int positiveAmount = 0;
        String fileLine;
        String regex = ";";
        String[] fileLineContent;

        try (Scanner reader = new Scanner(csvfile)) {
            // skip header when scanning
            reader.nextLine();

            while (reader.hasNext()) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(regex);

                // since piscofins values are on column 37, I need the product to have at least
                // this many columns
                if (fileLineContent.length > 36) {
                    if (fileLineContent[37].equals("Negativa")) {
                        negativeAmount++;
                    } else if (fileLineContent[37].equals("Neutra")) {
                        neutralAmount++;
                    } else if (fileLineContent[37].equals("Positiva")) {
                        positiveAmount++;
                    } else {
                        System.out.println("Erro: " + fileLine);
                    }
                }
            }

            createTable(calculatePercentages(negativeAmount, neutralAmount, positiveAmount));

        } catch (Exception e) {
            System.out.println("Houve um erro na leitura do arquivo. Tente novamente.");
        }
    }

    // method to calculate percentages
    private double[] calculatePercentages(int negativeAmount, int neutralAmount, int positiveAmount) {
        double[] percentages = new double[4];

        double total = (double) negativeAmount + neutralAmount + positiveAmount;
        double negativePercent = negativeAmount / total * 100;
        double neutralPercent = neutralAmount / total * 100;
        double positivePercent = positiveAmount / total * 100;
        total = negativePercent + neutralPercent + positivePercent;

        percentages[0] = negativePercent;
        percentages[1] = neutralPercent;
        percentages[2] = positivePercent;
        percentages[3] = total;

        return percentages;
    }

    // method that displays more information on the medicine
    private void createTable(double[] percentages) {
        double negativePercent = percentages[0];
        double neutralPercent = percentages[1];
        double positivePercent = percentages[2];
        double total = percentages[3];

        System.out.println("CLASSIFICAÇÃO\tPERCENTUAL\tGRAFICO");
        System.out.printf("Negativa\t%.2f%%\t\t%s %n", negativePercent, asteriscAmount(negativePercent));
        System.out.printf("Neutra\t\t%.2f%%\t\t%s %n", neutralPercent, asteriscAmount(neutralPercent));
        System.out.printf("Positiva\t%.2f%%\t\t%s %n", positivePercent, asteriscAmount(positivePercent));
        System.out.printf("TOTAL\t\t%.2f%%", total);
    }

    // method to return asterisc String
    private String asteriscAmount(double percent) {
        String asteriscs = "";

        for (int i = 0; i < percent; i++) {
            asteriscs += "*";
        }

        return asteriscs;
    }
}
