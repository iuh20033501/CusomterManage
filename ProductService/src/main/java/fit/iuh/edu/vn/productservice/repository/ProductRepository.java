package fit.iuh.edu.vn.productservice.repository;

import fit.iuh.edu.vn.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductRepository extends JpaRepository<Product,String> {

}
