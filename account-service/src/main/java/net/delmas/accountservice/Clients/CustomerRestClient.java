package net.delmas.accountservice.Clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.delmas.accountservice.Model_Association.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customer/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomer")
    List<Customer> allCustomers();

    default Customer getDefaultCustomer(Long id, Exception e) {
        return Customer.builder()
                .id(id)
                .nom("Not Vailable")
                .prenom("Not Vailable")
                .email("Not Vailable")
                .build();
    }

    default List<Customer> getAllCustomer(Exception e) {
        return List.of();
    }
}
