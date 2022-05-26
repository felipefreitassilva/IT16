package Etapa2.src;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            int escolha;
            
            try {
                do {
                    System.out.println("\n/=======================================================\\");
                    System.out.println("|	BEM-VINDO AO PROGRAMA	");
                    System.out.println("|");
                    System.out.println("| ESCOLHA UMA DAS OPÇÕES ABAIXO PARA INICIAR	");
                    System.out.println("| (1) CONSULTAR MEDICAMENTO PELO NOME	");
                    System.out.println("| (2) BUSCA PELO CÓDIGO DE BARRAS	");
                    System.out.println("| (3) COMPARATIVO DA LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS) ");
                    System.out.println("| (4) TERMINAR O PROGRAMA	");
                    System.out.println("\\======================================================/");
                    escolha = input.nextInt();
                    
                    switch (escolha) {
                        case 1:
                            ConsultarMedicamentoPeloNome.main(args);
                            break;
                        case 2:
                            BuscaPeloCodigoDeBarras.main(args);
                            break;
                        case 3:
                            ComparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS.main(args);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Valor inválido. Tente Novamente.");
                            break;
                    }
                    
                } while (escolha != 4);
                
                System.out.println("Obrigado por utilizar!\n");
                
            } catch (Exception e) {
                // System.out.println("Error: "+e);
                System.out.println("Valor inválido, favor reinicie o programa!");
            }
        }
	}
}