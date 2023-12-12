package com.fintech.bannkingapp.controller;

import com.fintech.bannkingapp.dto.*;
import com.fintech.bannkingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Account Management APIs")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(
            summary = "Create New User Account",
            description = "End point creates new User and assigns an account number to the user."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
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
