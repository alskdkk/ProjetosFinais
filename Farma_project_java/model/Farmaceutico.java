package model;

public class Farmaceutico {

    private String nome;
    private String crf;

    public Farmaceutico(String nome, String crf) {
        this.nome = nome;
        this.crf = crf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrf() {
        return crf;
    }

    public void setCrf(String crf) {
        this.crf = crf;
    }
}
