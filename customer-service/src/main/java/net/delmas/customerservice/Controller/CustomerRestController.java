package net.delmas.customerservice.Controller;

import jakarta.ws.rs.GET;
import lombok.AllArgsConstructor;
import net.delmas.customerservice.Model.Customer;
import net.delmas.customerservice.Repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer customerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
