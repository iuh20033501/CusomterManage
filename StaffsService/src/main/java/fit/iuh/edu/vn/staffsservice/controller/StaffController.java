package fit.iuh.edu.vn.staffsservice.controller;
import fit.iuh.edu.vn.staffsservice.entity.Staff;
import fit.iuh.edu.vn.staffsservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @GetMapping("/getAll")
    public List<Staff> getAllStaff() {
        List<Staff> allStaff = staffRepository.findAll();
        return allStaff.stream()
                .filter(staff -> staff.getStatus() == 1)
                .collect(Collectors.toList());
    }

    @GetMapping("/findById/{id}")
    public Optional<Staff> getStaffById(String id) {
        return staffRepository.findById(id);
    }

    @PostMapping("/create")
    public Staff createStaff(Staff staff) {

        return staffRepository.save(staff);
    }
    @PutMapping("/update/{id}")
    public Staff updateStaff(@PathVariable String id, @RequestBody Staff staffDetails) {
        Staff staff =staffRepository.findById(id).orElse(null);
        if (staff != null) {
            staff.setStaffName(staffDetails.getStaffName());
            staff.setPhone(staffDetails.getPhone());
            staff.setEmail(staffDetails.getEmail());
            return staffRepository.save(staff);
        }
        return null;
    }

    @PutMapping("/delete/{id}")
    public Staff deleteStaff(@PathVariable String id) {
        Staff staff =staffRepository.findById(id).orElse(null);
        if (staff != null) {
            staff.setStatus(1);
            return  staffRepository.save(staff);
        }
        return null;
    }
}
