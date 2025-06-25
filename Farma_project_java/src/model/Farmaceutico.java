package model;

public class Farmaceutico extends Funcionario {

    private String crf;

    public Farmaceutico(String nome, String cpf, String crf) {
        super(nome, cpf, "Farmacêutico");
        this.crf = crf;
    }

    public String getCrf() {
        return crf;
    }

    public void setCrf(String crf) {
        this.crf = crf;
    }
}
