package net.delmas.customerservice;

import net.delmas.customerservice.Model.Customer;
import net.delmas.customerservice.Repository.CustomerRepository;
import net.delmas.customerservice.config.GlobalConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			List<Customer> customerList = List.of(
					Customer.builder()
							.nom("Doe")
							.prenom("John")
							.email("blabla@gmail.com")
							.build(),
					Customer.builder()
							.nom("Ekra")
							.prenom("Jane")
							.email("bla@gmail.com")
							.build()
			);
			customerRepository.saveAll(customerList);
		};
	}

}
