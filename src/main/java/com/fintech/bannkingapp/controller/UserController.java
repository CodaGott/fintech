package com.fintech.bannkingapp.controller;

import com.fintech.bannkingapp.dto.BankResponse;
import com.fintech.bannkingapp.dto.UserDto;
import com.fintech.bannkingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BankResponse> createUser(@RequestBody UserDto userDto){
        return userService.createAccount(userDto);
    }
}
