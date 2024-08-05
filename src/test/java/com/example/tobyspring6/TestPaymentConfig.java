package com.example.tobyspring6;

import com.example.tobyspring6.payment.ExRateProvider;
import com.example.tobyspring6.payment.ExRateProviderStub;
import com.example.tobyspring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public Clock clock(){
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
