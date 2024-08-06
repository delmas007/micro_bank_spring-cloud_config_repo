package net.delmas.accountservice.Controller;

import lombok.AllArgsConstructor;
import net.delmas.accountservice.Model.BankAccount;
import net.delmas.accountservice.Repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountRestController {
    BankAccountRepository bankAccountRepository;

    @GetMapping("/accounts")
    public List<BankAccount> accountList() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElse(null);
    }
}
