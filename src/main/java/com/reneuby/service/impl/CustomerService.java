package com.reneuby.service.impl;

import com.reneuby.dao.CustomerRepository;
import com.reneuby.domain.Customer;
import com.reneuby.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {
    @Autowired
    public CustomerService(@Qualifier("customerRepositoryImpl") CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Long save(Customer entity) {
        repository.save(entity);
        return null;
    }

    @Override
    public Customer get(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Customer entity) {
        repository.delete(entity);
    }

    @Override
    public boolean update(Customer entity) {
        return repository.update(entity);
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return repository.getCustomerByPhoneNumber(phoneNumber);
    }
}
