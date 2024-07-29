package com.example.tobyspring6;

import com.example.tobyspring6.payment.ExRateProvider;
import com.example.tobyspring6.payment.ExRateProviderStub;
import com.example.tobyspring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
