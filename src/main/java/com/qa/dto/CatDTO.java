package com.qa.dto;

import java.util.ArrayList;
import java.util.List;

public class CatDTO {

    private Long id;
    private String level;
    private List<PlayerDTO> player = new ArrayList<>();

    public CatDTO() {
    }

    public CatDTO(String level) {
        super();
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<PlayerDTO> getPlayer() {
        return player;
    }

    public void setPlayer(List<PlayerDTO> player) {
        this.player = player;
    }
}
