package fit.iuh.edu.vn.staffsservice;

import fit.iuh.edu.vn.staffsservice.controller.StaffController;
import fit.iuh.edu.vn.staffsservice.entity.Staff;
import fit.iuh.edu.vn.staffsservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
    LettuceConnectionFactory jedLettuceConnectionFactory(){
        return new LettuceConnectionFactory();
    }
    @Bean
    RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedLettuceConnectionFactory());
        return redisTemplate;
    }

}
