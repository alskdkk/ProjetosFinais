package model;

import java.time.LocalDate;

public class MedicamentoRestrito extends Produto {

    private boolean receitaApresentada;

    public MedicamentoRestrito(int id, String nome, double preco, double peso, LocalDate validade, int qtd, double taxaLucro, Endereco endereco) {
        super(id, nome, preco, peso, validade, qtd, "restrito", taxaLucro, endereco);
        this.receitaApresentada = false;
    }

    public void apresentarReceita() {
        this.receitaApresentada = true;
    }

    @Override
    public boolean podeSerVendido() {
        return getValidade().isAfter(LocalDate.now()) && receitaApresentada;
    }

    public double calcularPrecoVendaRestrito() {
        return getPrecoBase() + (getPrecoBase() * getTaxaLucro() / 100.0);
    }

    public boolean isReceitaApresentada() {
        return receitaApresentada;
    }

    public void setReceitaApresentada(boolean receitaApresentada) {
        this.receitaApresentada = receitaApresentada;
    }
}
