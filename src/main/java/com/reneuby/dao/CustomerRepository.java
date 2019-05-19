package com.reneuby.dao;

import com.reneuby.domain.Customer;

public interface CustomerRepository extends CommonRepository<Customer> {
    Customer getCustomerByPhoneNumber(String phoneNumber);
}