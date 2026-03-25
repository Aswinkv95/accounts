package com.Micro1Accounts.Entity;

import com.Micro1Accounts.dto.AccountsDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 5,max = 30,message = "Name length between 5 and 30")
    private String name;

    @NotEmpty(message = "name can't be empty")
    @Email(message  ="Not in email format")
    private String email;


    @Column(name="mobile_number")
    @NotEmpty(message = "name can't be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number should be 10 digits")
    private String mobileNumber;



}
