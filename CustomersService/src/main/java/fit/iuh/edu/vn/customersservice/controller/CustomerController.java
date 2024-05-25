package fit.iuh.edu.vn.customersservice.controller;

import fit.iuh.edu.vn.customersservice.entity.Customer;
import fit.iuh.edu.vn.customersservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/getAll")
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers.stream()
                .filter(customer -> customer.getDeleted() == 0)
                .collect(Collectors.toList());
    }
    @GetMapping("/findById/{id}")
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    @PostMapping("/create")
    public Customer createCustomers(Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setCustomerName(customerDetails.getCustomerName());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setEmail(customerDetails.getEmail());
            return customerRepository.save(customer);
        }
        return null;
    }

    @PutMapping("/delete/{id}")
    public Customer deleteCustomer(@PathVariable String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setDeleted(1);
            return customerRepository.save(customer);
        }
        return null;
    }
}
