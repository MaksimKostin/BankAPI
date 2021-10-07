package com.example.bankapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CardReqDTO {
    private long accountId;
    private String number;
    private String pin;
    private String cvv;
}
