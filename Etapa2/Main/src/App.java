package Etapa2.Main.src;

import java.util.Scanner;

public class App {

    ConsultarMedicamentoPeloNome consultarMedicamentoPeloNome = new ConsultarMedicamentoPeloNome();
    BuscaPeloCodigoDeBarras buscaPeloCodigoDeBarras = new BuscaPeloCodigoDeBarras();
    ComparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS comparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS = new ComparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS();

    public void execute() {
        int choice;

        try {
            do {
                choice = menuChoice();
                handleChoice(choice);
            } while (choice != 4);
        } catch (Exception e) {
            System.out.println("Valor inválido, favor reinicie o programa!");
        }
    }

    // gets user choice from the menu
    private int menuChoice() {
        Scanner input = new Scanner(System.in);

        int choice;

        menuInterface();
        choice = input.nextInt();

        return choice;
    }

    // displays the interface with all menu options
    private void menuInterface() {
        System.out.println("\n/=======================================================\\");
        System.out.println("|\tBEM-VINDO AO PROGRAMA");
        System.out.println("|");
        System.out.println("| ESCOLHA UMA DAS OPÇÕES ABAIXO PARA INICIAR");
        System.out.println("| (1) CONSULTAR MEDICAMENTO PELO NOME");
        System.out.println("| (2) BUSCA PELO CÓDIGO DE BARRAS");
        System.out.println("| (3) COMPARATIVO DA LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS) ");
        System.out.println("| (4) TERMINAR O PROGRAMA");
        System.out.println("\\======================================================/");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                consultarMedicamentoPeloNome.lookForMedicine();
                break;
            case 2:
                buscaPeloCodigoDeBarras.search();
                break;
            case 3:
                comparativoDaListaDeConcessaoDeCreditoTributarioPISCOFINS.compare();
                break;
            case 4:
                System.out.println("Obrigado por utilizar!\n");
                break;
            default:
                System.out.println("Valor inválido!\n");
                break;
        }
    }
}