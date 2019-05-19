package com.reneuby.service.impl;

import com.reneuby.dao.CustomerRepository;
import com.reneuby.domain.Customer;
import com.reneuby.service.AbstractService;
import com.reneuby.service.MoneyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyServiceImpl extends AbstractService<Customer, CustomerRepository> implements MoneyService {

    public MoneyServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public void makeDeposit(Customer client, BigDecimal amount) {
        client.setAmountOfMoney(client.getAmountOfMoney().add(amount));
        update(client);
    }

    @Override
    public void makeWithdraw(Customer client, BigDecimal amount) {
        client.setAmountOfMoney(client.getAmountOfMoney().subtract(amount));
        update(client);
    }

    @Override
    public void makeTransfer(Customer client, Customer recipient, BigDecimal amount) {
        makeWithdraw(client, amount);
        makeDeposit(recipient, amount);
    }

    @Override
    public BigDecimal avaliableToWithdraw(Customer customer, String querySum) {
        return null;
    }

    @Override
    public Long save(Customer entity) {
        return repository.save(entity).getId();
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
}
