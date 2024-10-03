package com.devcaotics.fisctech.repositorios;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.fisctech.entites.Departamento;
import com.devcaotics.fisctech.entites.Falha;


public class FalhaRepositorio implements Repository<Falha, Integer> {

    public static final FalhaRepositorio current = new FalhaRepositorio();

    private FalhaRepositorio() {
    }

    @Override
    public void create(Falha falha) throws SQLException {
        String sql = "INSERT INTO falha (tipo_falha, data_ocorrido, departamento_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, falha.getFalhaTipo());
            pstm.setDate(2, new java.sql.Date(falha.getFalhaDataOcorrida().getTime()));
            pstm.setInt(3, falha.getFalhaDepartamento() != null ? falha.getFalhaDepartamento().getDepartamentoId() : null);
            pstm.execute();
        }
    }

    public List<Falha> readAll() throws SQLException {
        String sql = "SELECT f.*, d.nome_departamento AS departamento_nome FROM falha f "
                   + "LEFT JOIN departamento d ON f.departamento_id = d.id_departamento";
        List<Falha> falhas = new ArrayList<>();
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            while (result.next()) {
                Falha falha = new Falha();
                falha.setFalhaId(result.getInt("id_falha"));
                falha.setFalhaTipo(result.getString("tipo_falha"));
                falha.setFalhaDataOcorrida(result.getDate("data_ocorrido"));

                Departamento departamento = new Departamento();
                departamento.setDepartamentoId(result.getInt("departamento_id"));
                departamento.setDepartamentoNome(result.getString("departamento_nome"));

                falha.setFalhaDepartamento(departamento);
                falhas.add(falha);
            }
        }
        return falhas;
    }

    @Override
    public void update(Falha falha) throws SQLException {
        // Não implementado
    }

    @Override
    public Falha read(Integer id) throws SQLException {
        // Não implementado
        return new Falha();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        // Não implementado
    }
}
