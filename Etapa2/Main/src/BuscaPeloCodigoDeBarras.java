package Etapa2.Main.src;

import java.io.File;
import java.util.Scanner;

/*
    1.	[Buscar pelo código de barras] O programa deverá solicitar ao usuário o número correspondente ao código de barras de um produto (coluna de dados “EAN 1”, por exemplo ‘525516020019503’) e então:
    a.	Localizar todos os registros referentes a este produto, independentemente de terem sido comercializados ou não em 2020;
    b.	Dentre todos os registos encontrados, identificar o Preço Máximo ao Consumidor (alíquota de 0%, coluna de dados “PMC 0%”) mais alto e o mais baixo. Exibir na tela o mais alto, o mais baixo e a diferença entre eles.
*/

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

        final String FILE_PATH = "D:/Programming/GitHub/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File csvfile = new File(FILE_PATH);

        String fileLine;
        String regex = ",";
        String[] fileLineContent;
        boolean foundMedicine = false;

        try (Scanner reader = new Scanner(csvfile)) {
            // skip header when scanning
            reader.nextLine();

            while (reader.hasNext()) {
                fileLine = reader.nextLine();
                fileLineContent = fileLine.split(regex);

                if (fileLineContent[3].equals(barCode)) {
                    medicineInfo(fileLineContent, barCode);
                    foundMedicine = true;
                }
            }

            if (!foundMedicine) {
                System.out.printf("Infelizmente, não foram encontrados medicamentos por \"%s\".%n", barCode);
            }
        } catch (Exception e) {
            System.out.println("Houve um erro na leitura do arquivo. Tente novamente.");
        }
    }

    // method that displays more information on the medicine
    private void medicineInfo(String[] fileLineContent, String barCode) {
        System.out.printf("%n Dados do medicamento encontrado por \"%s\" %n", barCode);

        for (String info : fileLineContent) {
            System.out.println("| " + info);
        }
    }
}
