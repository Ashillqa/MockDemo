package com.qa.service;

import com.qa.domain.User;
import com.qa.dto.UserDTO;

import com.qa.exception.UserNotFoundException;
import com.qa.repo.userRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class userService {

    private final userRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public userService(userRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    ///////////////map to DTO///////////////////////////////////////////////
    private UserDTO mapToDto(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    ///////////////////////get all////////////////////////////////////////
    public List<UserDTO> readUser() {
        return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // create an Employee using the repo.save() again change to type DTO
    public UserDTO createUser(User user) {
        User tempUser = this.repo.save(user);
        return this.mapToDto(tempUser);
    }

    // find a repo by ID and also introduce new Exception - will help when updating and deleting again maptoDto
    public UserDTO findUserById(Long id) {
        return this.mapToDto(this.repo.findById(id).orElseThrow(UserNotFoundException::new));
    }

    // Now we can check Id when updating an employee
    public UserDTO updateUser(Long id, User user) {
        User update = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        update.setPassword(user.getPassword());
        return this.mapToDto(this.repo.save(update));
    }

    // Delete an employee again by first checking the ID actually exists
    public boolean deleteUser(Long id) {
        if (!this.repo.existsById(id)) {
            throw new UserNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }
}