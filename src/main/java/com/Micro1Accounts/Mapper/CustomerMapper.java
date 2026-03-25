package com.Micro1Accounts.Mapper;

import com.Micro1Accounts.Entity.Customer;
import com.Micro1Accounts.dto.CustomerDto;

public class CustomerMapper {

    public static CustomerDto mapTocustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapTocustomer( CustomerDto customerDto,Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}
