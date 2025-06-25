package model;

import java.util.ArrayList;
import java.util.List;

public class Farmacia {

    private String nome;
    private String nomeFarmaceuticoResponsavel;
    private List<Produto> estoque;
    private List<Funcionario> funcionarios;

    public Farmacia(String nome, String nomeFarmaceuticoResponsavel) {
        this.nome = nome;
        this.nomeFarmaceuticoResponsavel = nomeFarmaceuticoResponsavel;
        this.estoque = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        estoque.add(produto);
    }

    public void removerProduto(Produto produto) {
        estoque.remove(produto);
    }

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
