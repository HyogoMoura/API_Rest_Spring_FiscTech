package com.devcaotics.fisctech.entites;

public class Funcionario {
    private int funcionarioId;   
    private String funcionarioNome;
    private String funcionarioRegistro;
    private String funcionarioEmail;
    private Departamento funcionarioDepartamento;

    public Funcionario() {}

    public Funcionario(String funcionarioNome, String funcionarioRegistro, String funcionarioEmail, Departamento funcionarioDepartamento) {
        this.funcionarioNome = funcionarioNome;
        this.funcionarioRegistro = funcionarioRegistro;
        this.funcionarioEmail = funcionarioEmail;
        this.funcionarioDepartamento = funcionarioDepartamento;
    }

    public int getFuncionarioId() {
        return this.funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getFuncionarioNome() {
        return this.funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public String getFuncionarioRegistro() {
        return this.funcionarioRegistro;
    }

    public void setFuncionarioRegistro(String funcionarioRegistro) {
        this.funcionarioRegistro = funcionarioRegistro;
    }

    public String getFuncionarioEmail() {
        return this.funcionarioEmail;
    }

    public void setFuncionarioEmail(String funcionarioEmail) {
        this.funcionarioEmail = funcionarioEmail;
    }

    public Departamento getFuncionarioDepartamento() {
        return this.funcionarioDepartamento;
    }

    public void setFuncionarioDepartamento(Departamento funcionarioDepartamento) {
        this.funcionarioDepartamento = funcionarioDepartamento;
    }
}
