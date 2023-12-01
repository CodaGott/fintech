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
public class BalanceResponse {
    private String accountNumber;
    private BigDecimal accountBalance;
}
