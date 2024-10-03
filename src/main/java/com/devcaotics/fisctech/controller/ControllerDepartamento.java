package com.devcaotics.fisctech.controller;


import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcaotics.fisctech.entites.Departamento;
import com.devcaotics.fisctech.repositorios.DepartamentoRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/departamento")
public class ControllerDepartamento {

    @PostMapping("/create")
    public ResponseEntity<String> createDepartamento(@RequestBody Departamento departamento) {
        try {
            DepartamentoRepositorio.instance.create(departamento);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Departamento criado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao criar o departamento: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamento(@PathVariable Integer id) {
        try {
            Departamento departamento = DepartamentoRepositorio.instance.read(id);
            if (departamento != null) {
                return ResponseEntity.ok(departamento);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(null);
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Departamento>> getAllDepartamentos() {
        try {
            List<Departamento> departamentos = DepartamentoRepositorio.instance.readAll();
            return ResponseEntity.ok(departamentos);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartamento(@PathVariable Integer id, @RequestBody Departamento departamento) {
        try {
            departamento.setDepartamentoId(id);
            DepartamentoRepositorio.instance.update(departamento);
            return ResponseEntity.ok("Departamento atualizado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao atualizar o departamento: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartamento(@PathVariable Integer id) {
        try {
            DepartamentoRepositorio.instance.delete(id);
            return ResponseEntity.ok("Departamento deletado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao deletar o departamento: " + e.getMessage());
        }
    }
}
