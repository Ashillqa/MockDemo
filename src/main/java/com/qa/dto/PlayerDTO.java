package com.qa.dto;

import java.util.Objects;

public class PlayerDTO {

    private Long id;
    private String name;
    private Long goals;
    private Long assists;
    private Long tackles;

    public PlayerDTO() {
    }

    public PlayerDTO(String name, Long goals,Long assists,Long tackles) {
        this.name = name;
        this.goals = goals;
        this.assists = assists;
        this.tackles = tackles;
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

    public Long getAssists() { return assists; }

    public void setAssists(Long assists) { this.assists = assists; }

    public Long getTackles() { return tackles; }

    public void setTackles(Long tackles) { this.tackles = tackles; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return id.equals(playerDTO.id) &&
                name.equals(playerDTO.name) &&
                assists.equals(playerDTO.assists) &&
                tackles.equals(playerDTO.tackles) &&
                goals.equals(playerDTO.goals);
    }

   // @Override
  //  public int hashCode() {
      //  return Objects.hash(id, name, goals);
   // }
}
