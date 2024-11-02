package com.springtest.demo.config;

import com.springtest.demo.model.Player;
import com.springtest.demo.service.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CalculatorService calculatorService() {
        return new CalculatorService();
    }

}
