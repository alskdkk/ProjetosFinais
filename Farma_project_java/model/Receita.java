package model;

import java.time.LocalDate;

public class Receita {

    private String nomeMedico;
    private String crm;
    private String nomePaciente;
    private LocalDate validade;

    public Receita(String nomeMedico, String crm, String nomePaciente, LocalDate validade) {
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.nomePaciente = nomePaciente;
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }
}
