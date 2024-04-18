package fit.iuh.edu.vn.customersservice.repository;

import fit.iuh.edu.vn.customersservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
