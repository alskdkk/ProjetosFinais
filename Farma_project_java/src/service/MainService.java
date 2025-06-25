package service;

import model.Produto;
import java.time.LocalDate;

public class MainService {
    public static boolean validarProdutoParaVenda(Produto produto) {
        return produto.getValidade().isAfter(LocalDate.now()) && produto.getQuantidade() > 0;
    }

    public static boolean podeAdicionarNaPrateleira(double pesoAtual, Produto novoProduto) {
        return pesoAtual + (novoProduto.getPeso() * novoProduto.getQuantidade()) <= 20.0;
    }

    public static double calcularPrecoComLucro(Produto produto) {
        return produto.getPrecoBase() * (1 + produto.getTaxaLucro());
    }
}