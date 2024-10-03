package com.devcaotics.fisctech.entites;

import java.util.Date;

public class Falha {
    private int falhaId;
    private String falhaTipo;
    private Date falhaDataOcorrida;
    private Departamento falhaDepartamento;

    public Falha() {}

    public Falha(String falhaTipo, Date falhaDataOcorrida, Departamento falhaDepartamento) {        
        this.falhaTipo = falhaTipo;
        this.falhaDataOcorrida = falhaDataOcorrida;
        this.falhaDepartamento = falhaDepartamento;
    }

    public int getFalhaId() {
        return this.falhaId;
    }

    public void setFalhaId(int falhaId) {
        this.falhaId = falhaId;
    }

    public String getFalhaTipo() {
        return this.falhaTipo;
    }

    public void setFalhaTipo(String falhaTipo) {
        this.falhaTipo = falhaTipo;
    }

    public Date getFalhaDataOcorrida() {
        return this.falhaDataOcorrida;
    }

    public void setFalhaDataOcorrida(Date falhaDataOcorrida) {
        this.falhaDataOcorrida = falhaDataOcorrida;
    }

    public Departamento getFalhaDepartamento() {
        return this.falhaDepartamento;
    }

    public void setFalhaDepartamento(Departamento falhaDepartamento) {
        this.falhaDepartamento = falhaDepartamento;
    }
}
