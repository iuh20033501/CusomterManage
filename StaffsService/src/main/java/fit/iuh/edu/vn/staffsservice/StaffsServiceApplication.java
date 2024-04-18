package fit.iuh.edu.vn.staffsservice;

import fit.iuh.edu.vn.staffsservice.controller.StaffController;
import fit.iuh.edu.vn.staffsservice.entity.Staff;
import fit.iuh.edu.vn.staffsservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StaffsServiceApplication {
@Autowired
    StaffRepository staffRepository;
@Autowired
    StaffController staffController;
    public static void main(String[] args) {
        SpringApplication.run(StaffsServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Staff> listS = new ArrayList<>();
//                listS = staffController.getAllStaff();
                listS = staffRepository.findAll();
                System.out.println(listS);
            }
        };
    }
}
