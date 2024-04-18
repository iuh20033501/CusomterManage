package fit.iuh.edu.vn.staffsservice.repository;

import fit.iuh.edu.vn.staffsservice.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,String> {
}
