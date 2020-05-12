package com.qa.dto;

import java.util.Objects;

public class PlayerDTO {

    private Long id;
    private String name;
    private Long goals;

    public PlayerDTO() {
    }

    public PlayerDTO(String name, Long goals) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return id.equals(playerDTO.id) &&
                name.equals(playerDTO.name) &&
                goals.equals(playerDTO.goals);
    }

   // @Override
  //  public int hashCode() {
      //  return Objects.hash(id, name, goals);
   // }
}
