package net.delmas.accountservice.Model;

import jakarta.persistence.*;
import lombok.*;
import net.delmas.accountservice.Enum.AccountType;
import net.delmas.accountservice.Model_Association.Customer;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate creationAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Transient
    private Customer customer;

    private Long customerId;
}
