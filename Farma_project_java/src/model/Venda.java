package model;

import java.time.LocalDate;
import java.util.List;

public class Venda {

    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produtos;
    private LocalDate data;

    public Venda(Cliente cliente, Funcionario funcionario, List<Produto> produtos) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produtos = produtos;
        this.data = LocalDate.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public LocalDate getData() {
        return data;
    }

    public double calcularTotal() {
        return produtos.stream()
                .mapToDouble(p -> p.getPrecoBase() + (p.getPrecoBase() * p.getTaxaLucro() / 100.0))
                .sum();
    }
}
