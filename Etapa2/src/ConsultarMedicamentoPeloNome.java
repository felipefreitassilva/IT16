package Etapa2.src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsultarMedicamentoPeloNome {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String nomeMedicamento;

            System.out.print("Digite o nome do medicamento: ");
            nomeMedicamento = input.nextLine();

            consultarMedicamento(nomeMedicamento);
        }
    }

	public static void consultarMedicamento(String name) {
		
		final String FILE_PATH = "C:/Users/felip/Desktop/log/PUCRS/IT16/Etapa2/TA_PRECO_MEDICAMENTO.csv";
		File arquivoCSV = new File(FILE_PATH);
    
        try {
            String fileLine = new String();
            boolean haMedicamento = false;
            try (Scanner reader = new Scanner(arquivoCSV)) {
                reader.nextLine();
                
                while (reader.hasNext()) {
                    fileLine = reader.nextLine();
                    
                    String[] valoresEntreVirgulas = fileLine.split(";");
                    
                    System.out.println("/===================================================\\");
                    System.out.println("| Dados do medicamento "+name);
                    System.out.println("| Nome: " + valoresEntreVirgulas[0]);
                    System.out.println("| Produto: " + valoresEntreVirgulas[9]);
                    System.out.println("| Apresentação: " + valoresEntreVirgulas[10]);
                    System.out.println("| Valor PF Sem Impostos: " + valoresEntreVirgulas[14]);
                    System.out.println("\\===================================================/");
                    haMedicamento = true;
                    break;
                }
                
                if (!haMedicamento) {
                    System.out.println("Infelizmente, não há medicamentos.");
                }
                
            }
        } catch (FileNotFoundException e) {
        }
	}
}
