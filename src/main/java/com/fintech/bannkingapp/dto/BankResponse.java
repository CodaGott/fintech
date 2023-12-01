package com.fintech.bannkingapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponse {
    private String responseCode;
    private String responseMessage;
    private Object accountInfo;

    public BankResponse(String accountExistsCode, String accountExistsMessage) {
        this.responseCode = accountExistsCode;
        this.responseMessage = accountExistsMessage;
    }

}
