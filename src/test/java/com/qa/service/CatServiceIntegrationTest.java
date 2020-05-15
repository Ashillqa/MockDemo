package com.qa.service;


import com.qa.domain.Category;
import com.qa.domain.Player;
import com.qa.dto.CatDTO;
import com.qa.dto.PlayerDTO;
import com.qa.repo.catRepository;
import com.qa.repo.playerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatServiceIntegrationTest {
    @Autowired
    private catService service;
    @Autowired
    private catRepository repo;
    @Autowired
    private playerRepository prepo;
    @Autowired
    private ModelMapper mapper;

    private Category testCat;

    private Category testCatWithId;

    private CatDTO mapToDto(Category cat){
        return this.mapper.map(cat, CatDTO.class);
    }

    @Before
    public void setUp(){
        this.testCat = new Category("Good");
        this.repo.deleteAll();
        this.testCatWithId = this.repo.save(this.testCat);
    }

    @Test
    public void readCatTest(){
        assertThat(this.service.readCat())
                .isEqualTo(
                        Stream.of(this.mapToDto(testCatWithId)).collect(Collectors.toList())
                );
    }// calling the read method of service is equal to our test emp in list

    @Test
    public void createCatTest(){
        assertEquals(this.mapToDto(this.testCatWithId),this.service.createCat(testCat));
    }  //test emp(id) as dto is the same as calling actual create method of service on test emp

    @Test
    public void findEmpByIdTest(){
        assertThat(this.service.findCatById(this.testCatWithId.getId()))
                .isEqualTo(this.mapToDto(this.testCatWithId));
    } //calling findbyID of actual service should return the test emp with that id

    @Test
    public void deleteEmpTest(){
        assertThat(this.service.deleteCat(this.testCatWithId.getId()))
                .isFalse();
    }







}
