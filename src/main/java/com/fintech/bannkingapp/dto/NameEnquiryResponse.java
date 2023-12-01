package com.fintech.bannkingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NameEnquiryResponse {
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String gender;
    private String address;
    private String stateOfOrigin;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String phoneNumber;
    private String altPhoneNumber;
    private String status;
    private String accountType;
}
