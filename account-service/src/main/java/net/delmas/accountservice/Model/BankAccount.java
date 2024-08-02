package net.delmas.accountservice.Model;

import jakarta.persistence.*;
import net.delmas.accountservice.Enum.AccountType;
import net.delmas.accountservice.Model_Association.Customer;

import java.util.Date;

@Entity
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private Date creationAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Transient
    private Customer customer;

    private Long customerId;
}
