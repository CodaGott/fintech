package com.fintech.bannkingapp.controller;

import com.fintech.bannkingapp.dto.*;
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

    @PostMapping("/credit-account")
    public ResponseEntity<BankResponse> creditAccount(@RequestBody CreditDebitRequest request){
        return userService.creditAccount(request);
    }
    @PostMapping("/debit-account")
    public ResponseEntity<BankResponse> debitAccount(@RequestBody CreditDebitRequest request){
        return userService.debitAccount(request);
    }
    @PostMapping("/transfer")
    public ResponseEntity<BankResponse> transfer(@RequestBody TransferRequest request){
        return userService.transfer(request);
    }
}
