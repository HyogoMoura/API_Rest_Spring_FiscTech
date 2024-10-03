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

import com.devcaotics.fisctech.entites.Falha;
import com.devcaotics.fisctech.repositorios.FalhaRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/falhas")
public class ControllerFalha {

    @PostMapping("/create")
    public ResponseEntity<String> createFalha(@RequestBody Falha falha) {
        try {
            FalhaRepositorio.current.create(falha);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Falha criada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao criar a falha: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Falha>> getAllFalhas() {
        try {
            List<Falha> falhas = FalhaRepositorio.current.readAll();
            return ResponseEntity.ok(falhas);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Falha> getFalha(@PathVariable Integer id) {
        try {
            Falha falha = FalhaRepositorio.current.read(id);
            if (falha != null) {
                return ResponseEntity.ok(falha);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFalha(@PathVariable Integer id, @RequestBody Falha falha) {
        try {
            falha.setFalhaId(id);
            FalhaRepositorio.current.update(falha);
            return ResponseEntity.ok("Falha atualizada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao atualizar a falha: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFalha(@PathVariable Integer id) {
        try {
            FalhaRepositorio.current.delete(id);
            return ResponseEntity.ok("Falha deletada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao deletar a falha: " + e.getMessage());
        }
    }
}
