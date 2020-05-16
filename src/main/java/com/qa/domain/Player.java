package com.qa.domain;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long goals;
    private Long assists;
    private Long tackles;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    public Player() {
    }

    public Player(String name, Long goals,Long assists,Long tackles) {
        this.name = name;
        this.goals = goals;
        this.assists= assists;
        this.tackles=tackles;
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

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    public Long getTackles() {
        return tackles;
    }

    public void setTackles(Long tackles) {
        this.tackles = tackles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (goals == null) {
            if (other.goals != null)
                return false;
        } else if (goals.equals(other.goals))
            return false;
        if (assists == null) {
            if (other.assists != null)
                return false;
        } else if (assists.equals(other.assists))
            return false;
        if (tackles == null) {
            if (other.tackles != null)
                return false;
        } else if (tackles.equals(other.tackles))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((goals == null) ? 0 : goals.hashCode());
        result = prime * result + ((assists == null) ? 0 : assists.hashCode());
        result = prime * result + ((tackles == null) ? 0 : tackles.hashCode());
        return result;
    }
}
