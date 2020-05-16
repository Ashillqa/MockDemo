package com.qa.dto;

public class UserDTO {
    private long id;

    private String username;

    private String password;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (getId() != userDTO.getId()) return false;
        if (!getUsername().equals(userDTO.getUsername())) return false;
        return getPassword().equals(userDTO.getPassword());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }


}
