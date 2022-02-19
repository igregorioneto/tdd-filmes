package com.tdd.filmes.services;

import com.tdd.filmes.models.Filme;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    public Filme obterFilme(Long id) {

        if(id > 100) {
            return null;
        }

        return new Filme(
                id,
                "O Poderoso Chefão",
                "Filme norte-americano de 1972, dirigido por Francis Ford Coppola"
        );

    }

}
