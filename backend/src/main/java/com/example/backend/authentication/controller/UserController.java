package com.example.backend.authentication.controller;

import com.example.backend.authentication.dto.LoginUserDTO;
import com.example.backend.authentication.dto.NewUserDTO;
import com.example.backend.authentication.entity.NewUser;
import com.example.backend.authentication.repo.UserRepository;
import com.example.backend.authentication.response.LoginResponse;
import com.example.backend.authentication.response.SignUpResponse;
import com.example.backend.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody NewUserDTO newUserDTO){
        if (userRepository.existsByEmail(newUserDTO.getEmail())){
            return new ResponseEntity<>("Email already exists!", HttpStatus.BAD_REQUEST);
        }
        SignUpResponse response = userService.addUser(newUserDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO loginUserDTO){
       LoginResponse loginResponse = userService.loginUser(loginUserDTO);
       return ResponseEntity.ok(loginResponse);
    }
    @GetMapping(path = "/view")
    public List<NewUser> fetchUsers(){
        return userService.fetchUsers();
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUserById(id);
    }
}
