package net.delmas.accountservice.Controller;

import lombok.AllArgsConstructor;
import net.delmas.accountservice.Clients.CustomerRestClient;
import net.delmas.accountservice.Model.BankAccount;
import net.delmas.accountservice.Model_Association.Customer;
import net.delmas.accountservice.Repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountRestController {
    BankAccountRepository bankAccountRepository;
    CustomerRestClient customerRestClient;

    @GetMapping("/accounts")
    public List<BankAccount> accountList() {
        List<BankAccount> accountList = bankAccountRepository.findAll();
        accountList.forEach(acc ->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id) {
        BankAccount bankAccount= bankAccountRepository.findById(id).orElse(null);
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
