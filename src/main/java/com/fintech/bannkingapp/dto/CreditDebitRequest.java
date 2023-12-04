package com.fintech.bannkingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDebitRequest {
    private String accountNumber;
    private String amount;
}
