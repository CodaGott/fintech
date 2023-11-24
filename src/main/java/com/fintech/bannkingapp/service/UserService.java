package com.fintech.bannkingapp.service;

import com.fintech.bannkingapp.dto.BankResponse;
import com.fintech.bannkingapp.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BankResponse> createAccount(UserDto userDto);
}
