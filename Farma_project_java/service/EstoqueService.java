package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Endereco;
import model.Produto;

public class EstoqueService {

    private final String arquivo;

    public EstoqueService(String caminhoArquivo) {
        this.arquivo = caminhoArquivo;
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo de estoque: " + e.getMessage());
            }
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 12) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    double preco = Double.parseDouble(partes[2]);
                    double peso = Double.parseDouble(partes[3]);
                    LocalDate validade = LocalDate.parse(partes[4]);
                    int qtd = Integer.parseInt(partes[5]);
                    String tipo = partes[6];
                    double taxaLucro = Double.parseDouble(partes[7]);

                    String rua = partes[8];
                    int posicao = Integer.parseInt(partes[9]);
                    int altura = Integer.parseInt(partes[10]);
                    boolean prateleira = Boolean.parseBoolean(partes[11]);
                    Endereco endereco = new Endereco(rua, posicao, altura, prateleira);

                    Produto p = new Produto(id, nome, preco, peso, validade, qtd, tipo, taxaLucro, endereco);
                    produtos.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return produtos;
    }

    public void adicionarProduto(Produto p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(p.getId() + ";" + p.getNome() + ";" + p.getPrecoBase() + ";" + p.getPeso() + ";"
                    + p.getValidade() + ";" + p.getQuantidade() + ";" + p.getTipo() + ";" + p.getTaxaLucro() + ";"
                    + p.getEndereco().getRua() + ";" + p.getEndereco().getPosicao() + ";"
                    + p.getEndereco().getAltura() + ";" + p.getEndereco().isPrateleira());

            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public boolean deletarProduto(int id) {
        List<Produto> produtos = listarProdutos();
        boolean removido = false;

        Iterator<Produto> iterator = produtos.iterator();
        while (iterator.hasNext()) {
            Produto p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                removido = true;
                break;
            }
        }

        if (removido) {
            salvarLista(produtos);
        }

        return removido;
    }

    public void salvarLista(List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Produto p : produtos) {
                bw.write(p.getId() + ";" + p.getNome() + ";" + p.getPrecoBase() + ";" + p.getPeso() + ";"
                        + p.getValidade() + ";" + p.getQuantidade() + ";" + p.getTipo() + ";" + p.getTaxaLucro() + ";"
                        + p.getEndereco().getRua() + ";" + p.getEndereco().getPosicao() + ";"
                        + p.getEndereco().getAltura() + ";" + p.getEndereco().isPrateleira());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar lista de produtos: " + e.getMessage());
        }
    }
}
