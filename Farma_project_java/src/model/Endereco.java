package model;

import java.util.List;

public class Endereco {

    private String rua;
    private int posicao;
    private int altura;
    private boolean prateleira;

    public Endereco(String rua, int posicao, int altura, boolean prateleira) {
        this.rua = rua;
        this.posicao = posicao;
        this.altura = altura;
        this.prateleira = prateleira;
    }

    public double pesoTotalAtual(List<Produto> produtos) {
        double total = 0;
        for (Produto p : produtos) {
            if (p.getEndereco() != null && p.getEndereco().getCodigo().equals(this.getCodigo())) {
                total += p.getPeso() * p.getQuantidade();
            }

            if (p.getEndereco() != null
                    && p.getEndereco().isPrateleira()
                    && p.getEndereco().getCodigo().equals(this.getCodigo())) {
                total += p.getPeso() * p.getQuantidade();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return getCodigo();
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isPrateleira() {
        return prateleira;
    }

    public void setPrateleira(boolean prateleira) {
        this.prateleira = prateleira;
    }

    public String getCodigo() {
        return rua + String.format("%02d", posicao) + (prateleira ? String.format("%02d", altura) : "");
    }

}
