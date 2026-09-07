package com.opscore.controller;

import com.opscore.dto.UserDTO;
import com.opscore.model.User;
import com.opscore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAllUsers();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findByID(id);
    }

    @PutMapping("/{userID}")
    public User update(@PathVariable Long userID,@RequestBody UserDTO userDTO){
        return userService.updateUser(userID,userDTO);
    }


    @PostMapping
    public User addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    };

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable ("userID")Long userID){
        userService.deleteUser(userID);
        return ResponseEntity.noContent().build();

    }


}
