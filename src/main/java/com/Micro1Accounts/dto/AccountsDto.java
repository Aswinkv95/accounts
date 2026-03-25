package com.Micro1Accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {

    private Long accountNumber;
    private String name;
    private String accountType;
    private String email;
    private String mobileNumber;
    private String branchAddress;

}
