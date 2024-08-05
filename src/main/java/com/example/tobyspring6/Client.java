package com.example.tobyspring6;

import com.example.tobyspring6.payment.Payment;
import com.example.tobyspring6.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfiguration.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);

        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment2);

        TimeUnit.SECONDS.sleep(3);

        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment3);
    }
}
