package fit.iuh.edu.vn.deliveryservice.repository;

import fit.iuh.edu.vn.deliveryservice.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,String> {
}
