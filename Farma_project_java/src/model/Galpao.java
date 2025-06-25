package model;

import java.util.ArrayList;
import java.util.List;

public class Galpao {

    private String estado;
    private String nomeFarmaceuticoResponsavel;
    private List<Produto> produtos;

    public Galpao(String estado, String nomeFarmaceuticoResponsavel) {
        this.estado = estado;
        this.nomeFarmaceuticoResponsavel = nomeFarmaceuticoResponsavel;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomeFarmaceuticoResponsavel() {
        return nomeFarmaceuticoResponsavel;
    }

    public void setNomeFarmaceuticoResponsavel(String nomeFarmaceuticoResponsavel) {
        this.nomeFarmaceuticoResponsavel = nomeFarmaceuticoResponsavel;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
