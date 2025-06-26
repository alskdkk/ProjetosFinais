package model;

import java.util.List;

public class Farmacia {

    private String nome;
    private String nomeFarmaceuticoResponsavel;
    private List<Produto> estoque;

    public Farmacia(String nome, String nomeFarmaceuticoResponsavel) {
        this.nome = nome;
        this.nomeFarmaceuticoResponsavel = nomeFarmaceuticoResponsavel;
    }

    public void adicionarProduto(Produto produto) {
        estoque.add(produto);
    }

    public void removerProduto(Produto produto) {
        estoque.remove(produto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFarmaceuticoResponsavel() {
        return nomeFarmaceuticoResponsavel;
    }

    public void setNomeFarmaceuticoResponsavel(String nomeFarmaceuticoResponsavel) {
        this.nomeFarmaceuticoResponsavel = nomeFarmaceuticoResponsavel;
    }

    public List<Produto> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }

}
