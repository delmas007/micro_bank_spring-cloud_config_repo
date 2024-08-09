package net.delmas.accountservice;

import net.delmas.accountservice.Clients.CustomerRestClient;
import net.delmas.accountservice.Enum.AccountType;
import net.delmas.accountservice.Model.BankAccount;
import net.delmas.accountservice.Repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        return args -> {
            customerRestClient.allCustomers().forEach(customer -> {
                BankAccount bank2 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("FCFA")
                        .balance(Math.random()*80000)
                        .type(AccountType.SAVING_ACCOUNT)
                        .customerId(customer.getId())
                        .creationAt(LocalDate.now())
                        .build();
                BankAccount bank1 =BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("FCFA")
                        .balance(Math.random()*50600)
                        .type(AccountType.SAVING_ACCOUNT)
                        .customerId(customer.getId())
                        .creationAt(LocalDate.now())
                        .build();
                bankAccountRepository.save(bank1);
                bankAccountRepository.save(bank2);
            });

        };
    }

}
