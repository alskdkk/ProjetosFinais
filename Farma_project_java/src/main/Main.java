package main;

import java.util.*;
import model.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        List<Produto> estoqueGalpao = new ArrayList<>();
        List<Produto> estoqueFarmacia = new ArrayList<>();

        GalpaoUI galpaoUI = new GalpaoUI();
        FarmaciaUI farmaciaUI = new FarmaciaUI(estoqueFarmacia);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema Principal ===");
            System.out.println("1 - Galpão");
            System.out.println("2 - Farmácia");
            System.out.println("0 - Sair");
            System.out.println("\n--------------");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) galpaoUI.iniciar();
            else if (opcao.equals("2")) farmaciaUI.iniciar();
            else if (opcao.equals("0")) break;
        }
    }
}