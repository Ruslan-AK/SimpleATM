package com.reneuby.view;

import com.reneuby.domain.Customer;
import com.reneuby.service.MoneyService;
import com.reneuby.service.impl.CustomerService;
import com.reneuby.validator.CustomerValidator;
import com.reneuby.validator.MoneyValidator;
import com.reneuby.validator.properties.MoneyRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

@Component
public class ConsoleView {

    private BufferedReader br;
    private CustomerService customerService;
    private MoneyService moneyService;
    private CustomerValidator customerValidator;
    private MoneyValidator moneyValidator;
    private Customer currentCustomer;

    public ConsoleView() {
    }

    @Autowired
    public ConsoleView(BufferedReader br, CustomerService customerService, MoneyService moneyService, CustomerValidator customerValidator, MoneyValidator moneyValidator) {
        this.br = br;
        this.customerService = customerService;
        this.moneyService = moneyService;
        this.customerValidator = customerValidator;
        this.moneyValidator = moneyValidator;
    }

    public void enterATM() {
        authorizationCurrentCustomer();
        chooseAction();
    }

    private void chooseAction() {
        boolean run = true;
        while (run) {
            System.out.println("Оберіть бажану дію:");
            System.out.println("1 - Вивести кошти");
            System.out.println("2 - Внести кошти");
            System.out.println("3 - Перевести кошти");
            System.out.println("4 - Вийти");
            switch (getInput()) {
                case "1": {
                    System.out.println("Введіть суму");
                    String amount = null;
                    if (moneyValidator.validateMoneyDenomination(amount = getInput())) {
                        moneyService.makeWithdraw(currentCustomer, new BigDecimal(amount));
                        System.out.println("Операція успішно закінчена");
                    } else {
                        System.out.println("ATM працює лише за номіналами: " + MoneyRating.values);
                    }
                    break;
                }
                case "2": {
                    System.out.println("Введіть суму");
                    String amount = null;
                    if (moneyValidator.validateMoneyDenomination(amount = getInput())) {
                        moneyService.makeDeposit(currentCustomer, new BigDecimal(amount));
                        System.out.println("Операція успішно закінчена");
                    } else {
                        System.out.println("ATM працює лише за номіналами: " + MoneyRating.values);
                    }
                    break;

                }
                case "3": {
                    System.out.println("Введіть суму");
                    String amount = null;
                    if (moneyValidator.validateMoneyDenomination(amount = getInput())) {
                        System.out.println("Введіть номер телефону отримувача");
                        Customer recipient = authorizationByInputPhoneNumber();
                        moneyService.makeTransfer(currentCustomer, recipient, new BigDecimal(amount));
                        System.out.println("Операція успішно закінчена");
                    } else {
                        System.out.println("ATM працює лише за номіналами: " + MoneyRating.values);
                    }
                    break;
                }
                case "4": {
                    run = false;
                    break;
                }
                default: {
                    System.out.println("Помилкова команда, спробуйте ще");
                    break;
                }
            }
        }
        System.out.println("На все добре, до побачення");
        System.exit(0);
    }

    private void authorizationCurrentCustomer() {
        System.out.println("Вітаю в банкоматі\nАвторизуйтеся, будь ласка за номером телефону:");
        currentCustomer = authorizationByInputPhoneNumber();
        System.out.println("Ви авторизовані як " + currentCustomer.getName() + " " + currentCustomer.getSurname());
    }

    private Customer authorizationByInputPhoneNumber() {
        boolean run = true;
        String id = null;
        while (run) {
            id = getInput();
            if (customerValidator.validateByPhoneNumber(id)) {
                run = false;
            } else {
                System.out.println("Номера " + id + " немає в бд");
                System.out.println("Спробуйте ще раз");
            }
        }
        return customerService.getCustomerByPhoneNumber(id);
    }

    private String getInput() {
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("Помилка вводу");
        }
        return input;
    }
}
