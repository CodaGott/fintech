package com.fintech.bannkingapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponse {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;
    private Object object;

    public BankResponse(String accountExistsCode, String accountExistsMessage) {
        this.responseCode = accountExistsCode;
        this.responseMessage = accountExistsMessage;
    }

    public BankResponse(String responseCode, String responseMessage, Object object) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.object = object;
    }
}
