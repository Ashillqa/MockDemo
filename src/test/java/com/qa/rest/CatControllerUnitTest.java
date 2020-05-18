package com.qa.rest;

import com.qa.domain.Category;
import com.qa.domain.Player;
import com.qa.dto.CatDTO;
import com.qa.service.catService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatControllerUnitTest {
    @InjectMocks
    private CatController controller;
    @Mock
    private catService service;

    private List<Category> cats;
    private Category testCat;
    private Category testCatWithId;
    private Category newCat;
    private Category updateCat;
    private Long id = 1L;
    private CatDTO cDTO;
    private final ModelMapper mapper = new ModelMapper();
    private CatDTO mapToDto(Category cat){return this.mapper.map(cat,CatDTO.class);}

    @Before
    public void setUp(){
        this.cats=new ArrayList<>();
        this.testCat= new Category("good");
        this.cats.add(testCat);
        this.testCatWithId = new Category(testCat.getLevel());
        this.testCatWithId.setId(this.id);
        this.cDTO = this.mapToDto(testCatWithId);
    }

    @Test
    public void getAllCatsTest(){
        when(service.readCat()).thenReturn(this.cats.stream().map(this::mapToDto).collect(Collectors.toList()));
        assertFalse("No cats found", this.controller.getAllCats().getBody().isEmpty());
        verify(service, times(1)).readCat();
    }

    @Test
    public void createCatTest(){
        when(this.service.createCat(testCat)).thenReturn(this.cDTO);
        assertEquals(this.controller.createCat(testCat), new ResponseEntity<CatDTO>(this.cDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createCat(testCat);
    }

    @Test
    public void deleteCatTestFalse(){
        this.controller.deleteCat(id);
        verify(service, times(1)).deleteCat(id);
    }

    @Test
    public void deleteCatTestTrue(){
        when(service.deleteCat(1L)).thenReturn(true);
        this.controller.deleteCat(1L);
        verify(service, times(1)).deleteCat(1L);
    }

    @Test
    public void getCatByIDTest(){
        when(this.service.findCatById(id)).thenReturn(this.cDTO);
        assertEquals(this.controller.catById(id), new ResponseEntity<CatDTO>(this.cDTO, HttpStatus.OK));
        verify(service, times(1)).findCatById(id);
    }


    @Test
    public void updateTest(){
        when(this.service.updateCat(id,updateCat)).thenReturn(cDTO);
        assertEquals(this.controller.updateCat(id,updateCat),new ResponseEntity<CatDTO>(this.cDTO,HttpStatus.OK));
    }
}
