package model;

import java.time.LocalDate;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private double peso;
    private LocalDate validade;
    private int qtd;
    private String tipo;
    private double taxaLucro;

    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Produto(int id, String nome, double preco, double peso, LocalDate validade, int qtd, String tipo, double taxaLucro, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.peso = peso;
        this.validade = validade;
        this.qtd = qtd;
        this.tipo = tipo;
        this.taxaLucro = taxaLucro;
        this.endereco = endereco;
    }

    public boolean podeSerVendido() {
        return validade != null && validade.isAfter(LocalDate.now());
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return preco;
    }

    public double getPeso() {
        return peso;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public int getQuantidade() {
        return qtd;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTaxaLucro() {
        return taxaLucro;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void setQuantidade(int quantidade) {
        this.qtd = quantidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTaxaLucro(double taxaLucro) {
        this.taxaLucro = taxaLucro;
    }
}
