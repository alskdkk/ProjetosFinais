package ui;

import java.time.LocalDate;
import java.util.*;
import model.*;
import service.*;

public class FarmaciaUI {

    private EstoqueService estoqueFarmacia;
    private EstoqueService estoqueGalpao;
    private Scanner scanner;
    private String nomeCliente;

    public FarmaciaUI(EstoqueService estoqueFarmacia, EstoqueService estoqueGalpao) {
        this.estoqueFarmacia = new EstoqueService("data/estoque_farmacia.txt");
        this.estoqueGalpao = estoqueGalpao;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n=== Farmácia ===");
            System.out.println("1 - Listar estoque");
            System.out.println("2 - Requisitar produto do galpão");
            System.out.println("3 - Realizar venda");
            System.out.println("0 - Voltar");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                listar();
            } else if (opcao.equals("2")) {
                requisitarProduto();
            } else if (opcao.equals("3")) {
                vender();
            } else if (opcao.equals("0")) {
                break;
            }
        }
    }

    private void listar() {
        List<Produto> produtos = estoqueFarmacia.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Estoque da farmácia vazio.");
            return;
        }
        System.out.printf("\n%-4s | %-15s | %-6s | %-5s | %-12s | %-5s | %-12s | %-6s%n",
                "ID", "Nome", "Preço", "Peso", "Validade", "Qtd", "Tipo", "Lucro");
        System.out.println("-----+-----------------+--------+-------+--------------+------+--------------+--------");
        for (Produto p : produtos) {
            System.out.printf("%-4d | %-15s | %-6.2f | %-5.2f | %-12s | %-5d | %-12s | %-6.2f%n",
                    p.getId(), p.getNome(), p.getPrecoBase(), p.getPeso(), p.getValidade(),
                    p.getQuantidade(), p.getTipo(), p.getTaxaLucro());
        }
    }

    private void requisitarProduto() {
        System.out.print("ID do produto no galpão: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produto = MainService.buscarProdutoPorId(estoqueGalpao, id);
        if (produto == null) {
            System.out.println("Produto não encontrado no galpão.");
            return;
        }

        if (!produto.podeSerVendido()) {
            System.out.println("Produto vencido. Não pode ser requisitado.");
            return;
        }

        estoqueFarmacia.adicionarProduto(produto);
        estoqueGalpao.deletarProduto(id);

        System.out.println("Produto requisitado com sucesso para a farmácia.");
    }

    private void vender() {
        System.out.print("ID do produto a vender: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o nome do cliente:");
        nomeCliente = scanner.nextLine();

        Produto produto = MainService.buscarProdutoPorId(estoqueFarmacia, id);
        if (produto == null) {
            System.out.println("Produto não encontrado na farmácia.");
            return;
        }

        if (!produto.podeSerVendido()) {
            System.out.println("Produto vencido. Não pode ser vendido.");
            return;
        }

        String tipo = produto.getTipo().toLowerCase();
        if (tipo.contains("restrito") || tipo.contains("receita")) {
            System.out.println("\nEste produto exige receita.");

            System.out.print("Nome do médico: ");
            String nomeMedico = scanner.nextLine();

            System.out.print("CRM do médico: ");
            String crm = scanner.nextLine();

            System.out.print("Válidade da receita: ");
            LocalDate validade = LocalDate.parse(scanner.nextLine());

            System.out.println("Receita registrada com sucesso para o paciente " + nomeCliente + ".");
            Receita receita = new Receita(nomeMedico, crm, nomeCliente, validade);
            ReceitaService.salvarReceita(receita);
        }

        System.out.print("Quantidade a vender: ");
        int qtdVenda = Integer.parseInt(scanner.nextLine());

        if (qtdVenda > produto.getQuantidade()) {
            System.out.println("Quantidade insuficiente em estoque.");
            return;
        }

        Farmacia farmacia = new Farmacia("FarmaMais+", "Joao Pedro");

        Venda venda = new Venda(nomeCliente, farmacia.getNomeFarmaceuticoResponsavel(), produto.calcularPrecoVenda(), LocalDate.now());
        VendaService.registrarVenda(venda);

        produto.setQuantidade(produto.getQuantidade() - qtdVenda);

        if (produto.getQuantidade() == 0) {
            estoqueFarmacia.deletarProduto(id);
        } else {
            estoqueFarmacia.salvarLista(List.of(produto));
        }


        System.out.println("Venda realizada com sucesso por " + farmacia.getNomeFarmaceuticoResponsavel() + ". Total: R$ " + produto.calcularPrecoVenda());
    }
}
