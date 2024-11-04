package com.springtest.demo.config;

import com.springtest.demo.model.Player;
import com.springtest.demo.service.CalculatorService;
import com.springtest.demo.service.PlayerStatistics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CalculatorService calculatorService() {
        return new CalculatorService();
    }

    @Bean
    public Player player() {
        return new Player("John Doe", 25);
    }

}
