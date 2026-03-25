package com.Micro1Accounts.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="account_number")
    @Id
    private Long accountNumber;

    @Column(name="account_type")
    @NotEmpty(message = "account_type can't be empty")
    private String accountType;

    @Column(name="BRANCH_ADDRESS")
    @NotEmpty(message = "branch_address can't be empty")
    private String branchAddress;

}
