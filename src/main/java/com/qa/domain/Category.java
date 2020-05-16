package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String level;

    @OneToMany(mappedBy ="category", fetch = FetchType.LAZY)
    private List<Player> player = new ArrayList<>();

    public Category() {
    }

    public Category(String level) {
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

    public List<Player> getPlayers() {
        return player;
    }

    public void setPlayers(List<Player> player) {
        this.player = player;
    }
}
