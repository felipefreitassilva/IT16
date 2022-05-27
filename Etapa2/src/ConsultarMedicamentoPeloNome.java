package Etapa2.src;

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    IMPORTANTE
    LEMBRAR DE USAR .CONTAINS()
*/
public class ConsultarMedicamentoPeloNome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        consultarMedicamento(nomeMedicamento(input));
    }

    private static String nomeMedicamento(Scanner input) {
        String nomeMedicamento;

        System.out.print("Digite o nome do medicamento: ");
        nomeMedicamento = input.nextLine();

        return nomeMedicamento;
    }

    public static void consultarMedicamento(String name) {

        final String FILE_PATH = "D:/Programming/GitHub/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
        File arquivoCSV = new File(FILE_PATH);

        try {
            String fileLine = "";
            boolean haMedicamento = false;

            try (Scanner reader = new Scanner(arquivoCSV)) {
                // Skip header
                reader.nextLine();

                // While there are lines to be read
                while (reader.hasNext()) {
                    fileLine = reader.nextLine();

                    String[] valoresEntreVirgulas = fileLine.split(";");

                    System.out.println("/===================================================\\");
                    System.out.println("| Dados do medicamento " + name);
                    System.out.println("| Nome: " + valoresEntreVirgulas[0]);
                    System.out.println("| Produto: " + valoresEntreVirgulas[9]);
                    System.out.println("| Apresentação: " + valoresEntreVirgulas[10]);
                    System.out.println("| Valor PF Sem Impostos: " + valoresEntreVirgulas[14]);
                    System.out.println("\\===================================================/");
                    haMedicamento = true;
                }

                if (!haMedicamento) {
                    System.out.println("Infelizmente, não há medicamentos.");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
