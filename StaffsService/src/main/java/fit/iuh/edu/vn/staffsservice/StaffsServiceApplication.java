package fit.iuh.edu.vn.staffsservice;

import fit.iuh.edu.vn.staffsservice.controller.StaffController;
import fit.iuh.edu.vn.staffsservice.entity.Staff;
import fit.iuh.edu.vn.staffsservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;

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
    CommandLineRunner commandLineRunner(StaffRepository mockRepository, RetryTemplate retryTemplate) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Staff> listS = retryTemplate.execute(context -> {
                    if (staffRepository == null) {
                        throw new RuntimeException("Simulated error: staffRepository is null");
                    }
                    return staffRepository.findAll();
                });
                System.out.println(listS);
                if (listS != null && !listS.isEmpty()) {
                    System.out.println("RetryTemplate đã hoạt động thành công.");
                } else {
                    System.out.println("RetryTemplate không hoạt động.");
                }
            }
        };
    }
}
