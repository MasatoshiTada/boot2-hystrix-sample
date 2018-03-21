package com.example.producer;

import com.example.producer.filter.LoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new LoggingFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}
