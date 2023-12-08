package com.fintech.bannkingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private String accountNumberToDebit;
    private String accountNumberToCredit;
    private BigDecimal amount;
}
