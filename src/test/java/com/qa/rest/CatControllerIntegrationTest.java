package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Category;
import com.qa.dto.CatDTO;
import com.qa.repo.catRepository;
import com.qa.service.catService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatControllerIntegrationTest {


    @Autowired
    private CatController controller;
    @Autowired
    private catService service;
    @Autowired
    private catRepository repo;

    private Category testCat;

    private Category testCatWithId;

    @Before
    public void setUp(){
        this.testCat = new Category("Good");
        this.repo.deleteAll();
        this.testCatWithId = this.repo.save(this.testCat);
    }

    @Test
    public void readCatTest(){
        assertThat(this.controller.getAllCats())
                .isEqualTo(
                        ResponseEntity.ok(this.service.readCat())
                );
    }






}
