package Etapa2.Main.src;

import java.io.File;
import java.util.Scanner;

/*
    1.	[Comparativo da LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS)] Com base somente nos produtos que foram comercializados em 2020, o programa deverá:
    a.	Consultar a coluna de dados “LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS)” para determinar o percentual de produtos classificados como “Negativa”, “Neutra” ou “Positiva” para esta coluna.
    b.	Mostrar os respectivos valores percentuais da seguinte maneira (dados fictícios):
    [* repare que a quantidade de asteriscos é proporcional ao respectivo percentual, por exemplo, neste caso são 21 asteriscos para a classificação Negativa.]

    CLASSIFICACAO
    PERCENTUAL	GRAFICO
    Negativa	21,33%	********************
    Neutra	45,18%	*********************************************
    Positiva	33,49%	*********************************
*/

public class ComparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS {

    // method that scans the csv file for the medicine acording to its name
    public void compareCreditPercentuals() {

        final String FILE_PATH = "D:/Programming/GitHub/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File csvfile = new File(FILE_PATH);

        int negativeAmount = 0;
        int neutralAmount = 0;
        int positiveAmount = 0;
        String fileLine;
        String regex = ",";
        String[] fileLineContent;

        try (Scanner reader = new Scanner(csvfile)) {
            // skip header when scanning
            reader.nextLine();

            while (reader.hasNext()) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(regex);

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

            createTable(negativeAmount, neutralAmount, positiveAmount);

        } catch (Exception e) {
            System.out.println("Houve um erro na leitura do arquivo. Tente novamente.");
        }
    }

    // method that displays more information on the medicine
    private void createTable(int negativeAmount, int neutralAmount, int positiveAmount) {
        double total = (double) negativeAmount + neutralAmount + positiveAmount;
        double negativePercent = negativeAmount / total * 100;
        double neutralPercent = neutralAmount / total * 100;
        double positivePercent = positiveAmount / total * 100;
        total = negativePercent + neutralPercent + positivePercent;

        System.out.println("CLASSIFICAÇÃO\tPERCENTUAL\tGRAFICO");
        System.out.printf("Negativa\t%.2f%%\t\t%s %n", negativePercent, asteriscAmount(negativePercent));
        System.out.printf("Neutra\t\t%.2f%%\t\t%s %n", neutralPercent, asteriscAmount(neutralPercent));
        System.out.printf("Positiva\t%.2f%%\t\t%s %n", positivePercent, asteriscAmount(positivePercent));
        System.out.printf("TOTAL\t\t%.2f%%", total);
    }

    private String asteriscAmount(double percent) {
        String asteriscs = "";

        for (int i = 0; i < percent; i++) {
            asteriscs += "*";
        }

        return asteriscs;
    }
}
