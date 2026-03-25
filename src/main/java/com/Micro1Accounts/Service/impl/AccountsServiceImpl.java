package com.Micro1Accounts.Service.impl;

import com.Micro1Accounts.Constants.AccountConstants;
import com.Micro1Accounts.Entity.Accounts;
import com.Micro1Accounts.Entity.Customer;
import com.Micro1Accounts.Exception.CustomerAlreadyExistException;
import com.Micro1Accounts.Exception.ResourceNotFoundException;
import com.Micro1Accounts.Mapper.AccountMapper;
import com.Micro1Accounts.Mapper.CustomerMapper;
import com.Micro1Accounts.Repository.AccountsRepository;
import com.Micro1Accounts.Repository.CustomerRepository;
import com.Micro1Accounts.Service.IAccountsService;
import com.Micro1Accounts.dto.AccountsDto;
import com.Micro1Accounts.dto.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    /**
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapTocustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        System.out.println("optionalCustomer--"+optionalCustomer.isPresent());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already registered with this mobile number "+customerDto.getMobileNumber());
        }
      //  customer.setCreatedAt(LocalDateTime.now());
       // customer.setCreatedBy("JOKER");

        Customer savedCustomer =  customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    /**
     *
     * @param customer
     * @return
     */

    private Accounts createNewAccount(Customer customer){
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber =1000000000L+new Random().nextInt(90000000);
        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountConstants.ADDRESS);
      //  newAccounts.setCreatedAt(LocalDateTime.now());
     //   newAccounts.setCreatedBy("JOKER");
        return newAccounts;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto  fetchAccount(String mobileNumber){

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
               ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
       );

        Accounts accounts = accountsRepository.findByCustomerId (customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapTocustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate= false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountType().toString())
            );
            AccountMapper.mapToAccounts(accountsDto,accounts);
            accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapTocustomer(customerDto, customer);

            customerRepository.save(customer);
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDelete = false;
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Account","MobileNumber",mobileNumber)
        );

        System.out.println("Customer Id "+customer.getCustomerId());
        accountsRepository.deleteByCustomerId (customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        isDelete = true;
        return isDelete;
    }


}
