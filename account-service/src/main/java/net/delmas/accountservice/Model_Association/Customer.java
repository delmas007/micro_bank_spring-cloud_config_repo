package net.delmas.accountservice.Model_Association;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class Customer {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
