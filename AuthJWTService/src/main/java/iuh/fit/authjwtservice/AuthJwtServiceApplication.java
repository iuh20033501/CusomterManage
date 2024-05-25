package iuh.fit.authjwtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class AuthJwtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthJwtServiceApplication.class, args);
    }



    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
