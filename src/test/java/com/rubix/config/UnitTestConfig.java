package com.rubix.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.rubix.repository.CubeCustomRepository;
import com.rubix.repository.impl.CubeRepositoryImpl;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rubix" })
@Configuration
public class UnitTestConfig {

    @Bean
    @Primary
    public CubeCustomRepository blabla() {
        return mock(CubeRepositoryImpl.class);
    }
}
