package fit.iuh.edu.vn.customersservice;

import fit.iuh.edu.vn.customersservice.controller.CustomerController;
import fit.iuh.edu.vn.customersservice.entity.Customer;
import fit.iuh.edu.vn.customersservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CustomersServiceApplication {
@Autowired
    private CustomerRepository customerRepository;
@Autowired
    private CustomerController customerController;

    public static void main(String[] args) {
        SpringApplication.run(CustomersServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {


//                Customer customer = new Customer("Huy Hùng","0929635572","oge@gmail.com",0);
//                customerRepository.save(customer);
//                               System.out.println(customer);

//               customerController.updateCustomer("CUS10", customer);
//               customerController.deleteCustomer("CUS10");
//                customerRepository.deleteById("CUS10");
                List<Customer> listCus = new ArrayList<>();
               listCus= customerController.getAllCustomers();
                System.out.println(listCus);

            }
        };
    }
}
