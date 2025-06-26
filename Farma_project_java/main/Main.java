package main;

import java.util.*;
import model.*;
import service.EstoqueService;
import ui.*;

public class Main {

    public static void main(String[] args) {

        EstoqueService estoqueGalpao = new EstoqueService("data/estoque_galpao.txt");
        EstoqueService estoqueFarmacia = new EstoqueService("data/estoque_farmacia.txt");

        GalpaoUI galpaoUI = new GalpaoUI();
        FarmaciaUI farmaciaUI = new FarmaciaUI(estoqueFarmacia, estoqueGalpao);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema Principal ===");
            System.out.println("1 - Galpão");
            System.out.println("2 - Farmácia");
            System.out.println("0 - Sair");
            System.out.println("\n--------------");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                galpaoUI.iniciar();
            } else if (opcao.equals("2")) {
                farmaciaUI.iniciar();
            } else if (opcao.equals("0")) {
                break;
            }
        }
    }
}
