package model;

import java.time.LocalDate;

public class Receita {

    private LocalDate validade;

    public Receita(LocalDate validade) {
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }
}
