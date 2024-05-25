package iuh.fit.edu.vn.analysisservice.repository;

import iuh.fit.edu.vn.analysisservice.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
