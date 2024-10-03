package com.devcaotics.fisctech.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.fisctech.entites.Departamento;

public class DepartamentoRepositorio implements Repository<Departamento, Integer> {

    public static final DepartamentoRepositorio instance = new DepartamentoRepositorio();

    private DepartamentoRepositorio() {}

    @Override
    public void create(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO departamento (nome_departamento) VALUES (?)";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, departamento.getDepartamentoNome());
            pstm.execute();
        }
    }

    @Override
    public Departamento read(Integer id) throws SQLException {
        String sql = "SELECT * FROM departamento WHERE id_departamento = ?";
        Departamento departamento = null;
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                departamento = new Departamento();
                departamento.setDepartamentoId(result.getInt("id_departamento"));
                departamento.setDepartamentoNome(result.getString("nome_departamento"));
            }
        }
        return departamento;
    }

    public List<Departamento> readAll() throws SQLException {
        String sql = "SELECT * FROM departamento";
        List<Departamento> departamentos = new ArrayList<>();
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                Departamento departamento = new Departamento();
                departamento.setDepartamentoId(result.getInt("id_departamento"));
                departamento.setDepartamentoNome(result.getString("nome_departamento"));
                departamentos.add(departamento);
            }
        }
        return departamentos;
    }

    @Override
    public void update(Departamento departamento) throws SQLException {
        String sql = "UPDATE departamento SET nome_departamento = ? WHERE id_departamento = ?";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, departamento.getDepartamentoNome());
            pstm.setInt(2, departamento.getDepartamentoId());
            pstm.execute();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM departamento WHERE id_departamento = ?";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
        }
    }
}
