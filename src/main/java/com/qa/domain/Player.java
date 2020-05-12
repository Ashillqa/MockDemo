package com.qa.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long goals;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    public Player() {
    }

    public Player(String name, Long goals) {
        this.name = name;
        this.goals = goals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGoals() {
        return goals;
    }

    public void setGoals(Long goals) {
        this.goals = goals;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id) &&
                name.equals(player.name) &&
                goals.equals(player.goals) &&
                category.equals(player.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, goals, category);
    }
}
