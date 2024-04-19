/*
 * @ (#) $NAME.java         4/19/2024
 *
 * Copyright (c) 2024 IUH.
 *
 */

package iuh.fit.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * @description: ...
 * @author: Vinh Trung Pham
 * @studentID: 20119821
 * @date: 4/19/2024
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
