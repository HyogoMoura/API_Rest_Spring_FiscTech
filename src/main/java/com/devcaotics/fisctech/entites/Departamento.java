package com.devcaotics.fisctech.entites;

public class Departamento {
    private int departamentoId;
    private String departamentoNome;

    public Departamento() {}

    public Departamento(String departamentoNome) {
        this.departamentoNome = departamentoNome;
    }

    public int getDepartamentoId() {
        return this.departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDepartamentoNome() {
        return this.departamentoNome;
    }

    public void setDepartamentoNome(String departamentoNome) {
        this.departamentoNome = departamentoNome;
    }
}
