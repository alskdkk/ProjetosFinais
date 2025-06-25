package ui;

import java.util.*;
import model.*;
import service.*;

public class FarmaciaUI {

    private List<Produto> estoque;
    private Scanner scanner;

    public FarmaciaUI(List<Produto> estoque) {
        this.estoque = estoque;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n=== Farmácia ===");
            System.out.println("1 - Listar estoque");
            System.out.println("2 - Realizar venda");
            System.out.println("0 - Voltar");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                listar(); 
            }else if (opcao.equals("2")) {
                vender(); 
            }else if (opcao.equals("0")) {
                break;
            }
        }
    }

    private void listar() {
        System.out.printf("\n%-4s | %-15s | %-6s | %-5s | %-12s | %-5s | %-12s | %-6s%n",
                "ID", "Nome", "Preço", "Peso", "Validade", "Qtd", "Tipo", "Lucro");
        System.out.println("-----+-----------------+--------+-------+--------------+------+--------------+--------");
        for (Produto p : estoque) {
            System.out.printf("%d | %s | %.2f | %d\n", p.getId(), p.getNome(), p.getPrecoBase(), p.getQuantidade());
        }
    }

    private void vender() {
        System.out.print("ID do produto: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto selecionado = null;
        for (Produto p : estoque) {
            if (p.getId() == id && MainService.validarProdutoParaVenda(p)) {
                selecionado = p;
                break;
            }
        }

        if (selecionado != null) {
            selecionado.setQuantidade(selecionado.getQuantidade() - 1);
            double total = MainService.calcularPrecoComLucro(selecionado);
            System.out.println("Venda realizada. Total: R$" + total);
        } else {
            System.out.println("Produto não disponível para venda.");
        }
    }
}
