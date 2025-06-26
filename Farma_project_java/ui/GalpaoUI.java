package ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Endereco;
import model.Galpao;
import model.Produto;
import service.EstoqueService;

public class GalpaoUI {

    private EstoqueService estoqueGalpao;
    private Scanner scanner;
    Galpao galpao = new Galpao("Paraná", "Carlos Alberto");

    public GalpaoUI() {
        this.estoqueGalpao = new EstoqueService("data/estoque_galpao.txt");
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Farmacêutico responsável: " + galpao.getNomeFarmaceuticoResponsavel());
            System.out.println("\n=== Galpão ===");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Adicionar produto");
            System.out.println("0 - Voltar");
            System.out.println("\n--------------");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                listar();
            } else if (opcao.equals("2")) {
                adicionar();
            } else if (opcao.equals("0")) {
                break;
            }
        }
    }

    private void listar() {
        List<Produto> produtos = estoqueGalpao.listarProdutos();

        if (produtos.size() == 0) {
            System.out.println("-------------------------\nEstoque esgotado, adicione produtos antes de listar\n-------------------------");
            return;
        }

        System.out.printf("\n%-4s | %-15s | %-6s | %-5s | %-12s | %-5s | %-12s | %-6s | %-10s%n",
                "ID", "Nome", "Preço", "Peso(Kg)", "Validade", "Qtd", "Tipo", "Lucro(%)", "Endereço");
        System.out.println("-----+-----------------+--------+-------+--------------+------+--------------+--------+----------");

        for (Produto p : produtos) {
            System.out.printf("%-4d | %-15s | %-6.2f | %-5.2f | %-12s | %-5d | %-12s | %-6.2f | %-10s%n",
                    p.getId(), p.getNome(), p.getPrecoBase(), p.getPeso(), p.getValidade(),
                    p.getQuantidade(), p.getTipo(), p.getTaxaLucro(), p.getEndereco());
        }
    }

    private void adicionar() {
        List<Produto> produtos = estoqueGalpao.listarProdutos();
        int id = produtos.size() + 1;

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine().replace(",", "."));

        System.out.print("Peso(Kg): ");
        double peso = Double.parseDouble(scanner.nextLine().replace(",", "."));

        System.out.print("Validade (AAAA-MM-DD): ");
        LocalDate validade = LocalDate.parse(scanner.nextLine());

        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(scanner.nextLine());

        System.out.print("\nEscolha um tipo:\n1 - sem receita\n2 - sob receita não restrito\n3 - restrito\n4 - perecivel\n5 - higiene pessoal\n");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        String type = getTipoProduto(tipo);

        double lucro;
        while (true) {
            System.out.print("Lucro (%): ");
            String input = scanner.nextLine().replace(",", ".");
            try {
                lucro = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }

        // endereco ------------------------------
        System.out.println("Endereço do produto:");
        System.out.print("Rua (2 letras): ");
        String rua = scanner.nextLine().toUpperCase();

        System.out.print("Posição (2 dígitos): ");
        int posicao = Integer.parseInt(scanner.nextLine());

        System.out.print("Altura (2 dígitos): ");
        int altura = Integer.parseInt(scanner.nextLine());

        System.out.print("É prateleira? (s/n): ");
        boolean prateleira = scanner.nextLine().trim().equalsIgnoreCase("s");

        Endereco endereco = new Endereco(rua, posicao, altura, prateleira);

        if (prateleira) {
            double pesoAtual = endereco.pesoTotalAtual(produtos);
            double pesoNovo = peso * qtd;
            if (pesoAtual + pesoNovo > 20.0) {
                System.out.println("Não é possível adicionar esse produto: limite de 20kg na prateleira será excedido.");
                return;
            }
        }

        final double precoFinal = preco;
        final double lucroFinal = lucro;
        final LocalDate validadeFinal = validade;

        Produto p = new Produto(id, nome, precoFinal, peso, validadeFinal, qtd, type, lucroFinal, endereco) {
            private Endereco e = endereco;

            public double calcularPrecoVenda() {
                return precoFinal * (1 + lucroFinal);
            }

            @Override
            public boolean podeSerVendido() {
                return validadeFinal.isAfter(LocalDate.now());
            }

            @Override
            public Endereco getEndereco() {
                return e;
            }
        };

        p.setEndereco(endereco);
        estoqueGalpao.adicionarProduto(p);
        System.out.println("Produto adicionado ao galpão.");
    }

    private String getTipoProduto(int tipo) {

        String type = "";
        switch (tipo) {
            case 1:
                type = "sem receita";
                break;
            case 2:
                type = "sob receita não restrito";
                break;
            case 3:
                type = "restrito";
                break;
            case 4:
                type = "perecivel";
                break;
            case 5:
                type = "higiene pessoal";
                break;
        }

        return type;
    }
}
