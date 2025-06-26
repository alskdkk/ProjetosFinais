package service;

import java.time.LocalDate;
import java.util.List;
import model.Produto;

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

    public static Produto buscarProdutoPorId(EstoqueService estoque, int id) {
        List<Produto> produtos = estoque.listarProdutos();
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
