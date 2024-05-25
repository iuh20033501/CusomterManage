package iuh.fit.edu.vn.analysisservice.repository;

import iuh.fit.edu.vn.analysisservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
