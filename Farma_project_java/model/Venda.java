package model;

import java.time.LocalDate;

public class Venda {

    private String cliente;
    private String funcionario;
    private Double valorTotal;
    private LocalDate data;

    public Venda(String cliente, String funcionario, Double produtos, LocalDate data) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.valorTotal = produtos;
        this.data = LocalDate.now();
    }

    public String getCliente() {
        return cliente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public Double getProdutos() {
        return valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
