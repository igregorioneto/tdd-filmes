package com.tdd.filmes.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;

import com.tdd.filmes.models.Filme;
import com.tdd.filmes.services.FilmeService;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest
public class FilmeControllerTest {

    @Autowired
    private FilmeController controller;

    @MockBean
    private FilmeService service;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.controller);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarFilme(){

        when(this.service.obterFilme(1L))
                .thenReturn(new Filme(1L, "O Poderoso Chefão", "Sem descrição"));

         given()
            .accept(ContentType.JSON)
        .when()
            .get("/filmes/{id}", 1L)
        .then()
            .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void deveRetornarNaoEncontrado_QuandoBuscarFilme() {

        when(this.service.obterFilme(5L))
                .thenReturn(null);

        given()
            .accept(ContentType.JSON)
        .when()
            .get("/filmes/{id}", 5L)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    public void deveRetornarBadRequest_QuandoBuscarFilme() {

        given()
            .accept(ContentType.JSON)
        .when()
            .get("/filmes/{id}", -1L)
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);

        verify(this.service, never()).obterFilme(-1L);

    }
}
