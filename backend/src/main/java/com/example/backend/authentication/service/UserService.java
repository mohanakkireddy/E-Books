package com.example.backend.authentication.service;

import com.example.backend.authentication.dto.LoginUserDTO;
import com.example.backend.authentication.dto.NewUserDTO;
import com.example.backend.authentication.entity.NewUser;
import com.example.backend.authentication.response.LoginResponse;
import com.example.backend.authentication.response.SignUpResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    SignUpResponse addUser(NewUserDTO newUserDTO);

    LoginResponse loginUser(LoginUserDTO loginUserDTO);

    List<NewUser> fetchUsers();

    void deleteUserById(int id);
}
