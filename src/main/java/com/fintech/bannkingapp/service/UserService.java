package com.fintech.bannkingapp.service;

import com.fintech.bannkingapp.dto.BankResponse;
import com.fintech.bannkingapp.dto.CreditDebitRequest;
import com.fintech.bannkingapp.dto.EnquiryRequest;
import com.fintech.bannkingapp.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BankResponse> createAccount(UserDto userDto);
    ResponseEntity<BankResponse> makeBalanceEnquiry(EnquiryRequest request);
    ResponseEntity<BankResponse> makeNameEnquiry(EnquiryRequest request);
    ResponseEntity<BankResponse> creditAccount(CreditDebitRequest request);
    ResponseEntity<BankResponse> debitAccount(CreditDebitRequest request);
}
