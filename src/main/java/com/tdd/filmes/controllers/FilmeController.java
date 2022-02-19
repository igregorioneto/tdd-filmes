package com.tdd.filmes.controllers;

import com.tdd.filmes.models.Filme;
import com.tdd.filmes.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping("/{id}")
    public ResponseEntity<Filme> obterFilme(@PathVariable Long id) {

        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }

        Filme filme = this.service.obterFilme(id);

        if(filme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filme);

    }

}
