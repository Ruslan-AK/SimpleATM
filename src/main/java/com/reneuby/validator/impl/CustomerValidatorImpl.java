package com.reneuby.validator.impl;

import com.reneuby.dao.CustomerRepository;
import com.reneuby.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidatorImpl implements CustomerValidator {

    @Autowired
    @Qualifier(value = "CustomerRepositoryImpl")
    CustomerRepository repository;

    @Override
    public boolean validateByPhoneNumber(String phoneNumber) {
        return repository.getCustomerByPhoneNumber(phoneNumber) != null;
    }
}
