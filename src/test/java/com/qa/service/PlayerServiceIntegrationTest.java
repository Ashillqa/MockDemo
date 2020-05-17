package com.qa.service;

import com.qa.domain.Player;
import com.qa.dto.PlayerDTO;
import com.qa.repo.playerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class PlayerServiceIntegrationTest {
    @Autowired
    private playerService service;

    @Autowired
    private playerRepository repo;

    @Autowired
    private ModelMapper mapper;

    private Player testPlayer;

    private Player testPlayerWithId;

    private PlayerDTO mapToDto(Player player){
        return this.mapper.map(player, PlayerDTO.class);
    }

    @Before
    public void setUp(){
        this.testPlayer = new Player("Tom",3L,4L,5L);
        this.repo.deleteAll();
        this.testPlayerWithId = this.repo.save(this.testPlayer);
    }

    @Test
    public void readEmpTest(){
        assertThat(this.service.readPlayer())
                .isEqualTo(
                        Stream.of(this.mapToDto(testPlayerWithId)).collect(Collectors.toList())
                );
    }     // calling the read method of service is equal to our test emp in list

    @Test
    public void createEmpTest(){
        assertEquals(this.mapToDto(this.testPlayerWithId),this.service.createPlayer(testPlayer));
    }  //test emp(id) as dto is the same as calling actual create method of service on test emp

    @Test
    public void findEmpByIdTest(){
        assertThat(this.service.findPlayerById(this.testPlayerWithId.getId()))
                .isEqualTo(this.mapToDto(this.testPlayerWithId));
    } //calling findbyID of actual service should return the test emp with that id

    @Test
    public void deleteEmpTest(){
        assertThat(this.service.deletePlayer(this.testPlayerWithId.getId()))
                .isFalse();
    }

}
