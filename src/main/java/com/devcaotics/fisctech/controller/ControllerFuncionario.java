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

import com.devcaotics.fisctech.entites.Funcionario;
import com.devcaotics.fisctech.repositorios.FuncionarioRepositorio;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/funcionario")
public class ControllerFuncionario {

    private final FuncionarioRepositorio funcionarioRepositorio;

    public ControllerFuncionario() {
        this.funcionarioRepositorio = FuncionarioRepositorio.instance;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createFuncionario(@RequestBody Funcionario funcionario) {
        try {
            funcionarioRepositorio.create(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Funcionário criado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao criar o funcionário: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionario(@PathVariable Integer id) {
        try {
            Funcionario funcionario = funcionarioRepositorio.read(id);

            if (funcionario != null) {
                return ResponseEntity.ok(funcionario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        try {

            List<Funcionario> funcionarios = funcionarioRepositorio.readAll();
            return ResponseEntity.ok(funcionarios);

        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        try {
            funcionario.setFuncionarioId(id);
            funcionarioRepositorio.update(funcionario);

            return ResponseEntity.ok("Funcionário atualizado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao atualizar o funcionário: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFuncionario(@PathVariable Integer id) {
        try {
            funcionarioRepositorio.delete(id);
            return ResponseEntity.ok("Funcionário deletado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao deletar o funcionário: " + e.getMessage());
        }
    }
}
