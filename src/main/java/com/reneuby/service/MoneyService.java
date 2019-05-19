package com.reneuby.service;

import com.reneuby.domain.Customer;

import java.math.BigDecimal;

public interface MoneyService {
    void makeDeposit(Customer client, BigDecimal amount);

    void makeWithdraw(Customer client, BigDecimal amount);

    void makeTransfer(Customer client, Customer recipient, BigDecimal amount);

    BigDecimal avaliableToWithdraw(Customer customer, String querySum);

}
