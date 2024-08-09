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
        customerRestClient.allCustomers().forEach(customer -> {
            BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .currency("FCFA")
                    .balance(Math.random()*80000)
                    .type(AccountType.SAVING_ACCOUNT)
                    .customerId(customer.getId())
                    .creationAt(LocalDate.now())
                    .build();
            BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .currency("FCFA")
                    .balance(Math.random()*50600)
                    .type(AccountType.SAVING_ACCOUNT)
                    .customerId(customer.getId())
                    .creationAt(LocalDate.now())
                    .build();
        });
        return args -> {
            List<BankAccount> bankAccountList = List.of(
                    BankAccount.builder()
                            .accountId(UUID.randomUUID().toString())
                            .currency("FCFA")
                            .balance(1500000)
                            .type(AccountType.SAVING_ACCOUNT)
                            .customerId(Long.valueOf(1))
                            .creationAt(LocalDate.now())
                            .build(),
                    BankAccount.builder()
                            .accountId(UUID.randomUUID().toString())
                            .currency("FCFA")
                            .balance(20000000)
                            .type(AccountType.SAVING_ACCOUNT)
                            .customerId(Long.valueOf(2))
                            .creationAt(LocalDate.now())
                            .build()
            );
            bankAccountRepository.saveAll(bankAccountList);
        };
    }

}
