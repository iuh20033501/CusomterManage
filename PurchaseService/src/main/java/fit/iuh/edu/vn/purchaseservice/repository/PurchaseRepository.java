package fit.iuh.edu.vn.purchaseservice.repository;

import fit.iuh.edu.vn.purchaseservice.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,String> {
}
