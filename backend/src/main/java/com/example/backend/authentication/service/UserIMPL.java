package com.example.backend.authentication.service;

import com.example.backend.authentication.dto.LoginUserDTO;
import com.example.backend.authentication.dto.NewUserDTO;
import com.example.backend.authentication.entity.NewUser;
import com.example.backend.authentication.repo.UserRepository;
import com.example.backend.authentication.response.LoginResponse;
import com.example.backend.authentication.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserIMPL implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public SignUpResponse addUser(NewUserDTO newUserDTO) {
        NewUser newUser = new NewUser(
                newUserDTO.getId(),
                newUserDTO.getUsername(),
                newUserDTO.getEmail(),
                this.passwordEncoder.encode(newUserDTO.getPassword())
                );
        userRepository.save(newUser);
        return new SignUpResponse(newUser.getUsername(), "User registered successfully", HttpStatus.CREATED);
    }

    @Override
    public LoginResponse loginUser(LoginUserDTO loginUserDTO) {
        String msg = "";
        NewUser user1 = userRepository.findByEmail(loginUserDTO.getEmail());
        if (user1 != null) {
            String password = loginUserDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<NewUser> employee = userRepository.findOneByEmailAndPassword(loginUserDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }

    @Override
    public List<NewUser> fetchUsers() {
        return (List<NewUser>)
                userRepository.findAll();
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
