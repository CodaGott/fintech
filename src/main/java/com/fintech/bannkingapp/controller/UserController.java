package com.fintech.bannkingapp.controller;

import com.fintech.bannkingapp.dto.BankResponse;
import com.fintech.bannkingapp.dto.EnquiryRequest;
import com.fintech.bannkingapp.dto.UserDto;
import com.fintech.bannkingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BankResponse> createUser(@RequestBody UserDto userDto){
        return userService.createAccount(userDto);
    }

    @GetMapping("/balance-enquiry")
    public ResponseEntity<BankResponse> balanceEnquiry(@RequestBody EnquiryRequest request){
        return userService.makeBalanceEnquiry(request);
    }

    @GetMapping("/name-enquiry")
    public ResponseEntity<BankResponse> nameEnquiry(@RequestBody EnquiryRequest request){
        return userService.makeNameEnquiry(request);
    }
}
