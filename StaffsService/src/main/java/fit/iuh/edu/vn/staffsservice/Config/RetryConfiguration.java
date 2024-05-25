package fit.iuh.edu.vn.staffsservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfiguration {
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // Cấu hình số lần retry và backoff policy
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5); // Số lần retry tối đa
        retryTemplate.setRetryPolicy(retryPolicy);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(5000); // Thời gian chờ giữa các lần retry (milliseconds)
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }

}
