package com.devcaotics.fisctech.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.fisctech.entites.Departamento;
import com.devcaotics.fisctech.entites.Funcionario;

public class FuncionarioRepositorio implements Repository<Funcionario, Integer> {

    public static final FuncionarioRepositorio instance = new FuncionarioRepositorio();

    private FuncionarioRepositorio() {}

    @Override
    public void create(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome_funcionario, matricula_funcionario, email_funcionario, departamento_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, funcionario.getFuncionarioNome());
            pstm.setString(2, funcionario.getFuncionarioRegistro());
            pstm.setString(3, funcionario.getFuncionarioEmail());
            pstm.setInt(4, funcionario.getFuncionarioDepartamento().getDepartamentoId());
            pstm.execute();
        }
    }

    @Override
    public Funcionario read(Integer id) throws SQLException {
        String sql = "SELECT f.*, d.nome_departamento AS departamento_nome FROM funcionario f "
                   + "LEFT JOIN departamento d ON f.departamento_id = d.id_departamento WHERE f.id_funcionario = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                return mapFuncionario(result);
            }
        }
        return null;
    }

    public List<Funcionario> readAll() throws SQLException {
        String sql = "SELECT f.*, d.nome_departamento AS departamento_nome FROM funcionario f "
                   + "LEFT JOIN departamento d ON f.departamento_id = d.id_departamento";

        List<Funcionario> funcionarios = new ArrayList<>();
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                funcionarios.add(mapFuncionario(result));
            }
        }
        return funcionarios;
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome_funcionario = ?, matricula_funcionario = ?, email_funcionario = ?, departamento_id = ? WHERE id_funcionario = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, funcionario.getFuncionarioNome());
            pstm.setString(2, funcionario.getFuncionarioRegistro());
            pstm.setString(3, funcionario.getFuncionarioEmail());
            pstm.setInt(4, funcionario.getFuncionarioDepartamento().getDepartamentoId());
            pstm.setInt(5, funcionario.getFuncionarioId());
            pstm.execute();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
        }
    }

    private Funcionario mapFuncionario(ResultSet result) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setFuncionarioId(result.getInt("id_funcionario"));
        funcionario.setFuncionarioNome(result.getString("nome_funcionario"));
        funcionario.setFuncionarioRegistro(result.getString("matricula_funcionario"));
        funcionario.setFuncionarioEmail(result.getString("email_funcionario"));

        Departamento departamento = new Departamento();
        departamento.setDepartamentoId(result.getInt("departamento_id"));
        departamento.setDepartamentoNome(result.getString("departamento_nome"));

        funcionario.setFuncionarioDepartamento(departamento);
        return funcionario;
    }
}

