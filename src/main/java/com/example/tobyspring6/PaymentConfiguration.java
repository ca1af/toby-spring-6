package com.example.tobyspring6;

import com.example.tobyspring6.exrate.CachedExRateProvider;
import com.example.tobyspring6.payment.ExRateProvider;
import com.example.tobyspring6.exrate.WebApiExRateProvider;
import com.example.tobyspring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }

    @Bean
    public ExRateProvider cachedExRateProvider(){
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone();
    }
}
