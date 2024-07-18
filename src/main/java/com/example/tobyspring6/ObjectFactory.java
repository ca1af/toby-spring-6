package com.example.tobyspring6;

import com.example.tobyspring6.exrate.CachedExRateProvider;
import com.example.tobyspring6.payment.ExRateProvider;
import com.example.tobyspring6.exrate.WebApiExRateProvider;
import com.example.tobyspring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }

    @Bean
    public ExRateProvider cachedExRateProvider(){
        return new CachedExRateProvider(exRateProvider());
    }
}
