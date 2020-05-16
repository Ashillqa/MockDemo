package com.qa.rest;

import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {

    private final userService service;

    public userController(userService service) {
        this.service = service;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.service.readUser());
    }

    // creating is a POST method so post mapping and you take in a emp so requestBody
    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user){
        return new ResponseEntity<UserDTO>(this.service.createUser(user), HttpStatus.CREATED);
    }
    // need to do find by ID and this will take a pathVariable rather than body
    @GetMapping("/userById/{id}")
    public ResponseEntity<UserDTO> userById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findUserById(id));
    }
    // update is to PUT so put mapping and again requestBody but two parameters
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(this.service.updateUser(id,user));
    }
    // Deleting employee again takes a path variable of Long Id
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return this.service.deleteUser(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }
}
