package com.example.mypenavigatorapi.users.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDto {
    private Long id;
    private String name;
    private String address;
    private String ruc;
    private String logoUrl;
}
