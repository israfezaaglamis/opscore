package com.opscore.service;

import com.opscore.dto.UserDTO;
import com.opscore.exception.UserAlreadyExistException;
import com.opscore.exception.UserNotFoundException;
import com.opscore.model.User;
import com.opscore.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User addUser(UserDTO userDTO) {
        //first check if email is correct or already exist

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistException(userDTO.getEmail() +
                    " email adresi zaten kayıtlı ");
        }

        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);

    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByID (Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }


    public User updateUser(Long userID,UserDTO userDTO){
        User user = userRepository.findById(userID)
                .orElseThrow(()-> new UserNotFoundException("user not found"));
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long userID){
        User user = userRepository.findById(userID)
                .orElseThrow(()-> new UserNotFoundException("user not found"));
        userRepository.delete(user);
    }

}