package com.example.tobyspring6.payment;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        PaymentService paymentService2 = beanFactory.getBean(PaymentService.class);

        Assert.isTrue(paymentService == paymentService2, "SAME");

        ObjectFactory objectFactory = beanFactory.getBean(ObjectFactory.class);
        PaymentService obj1 = objectFactory.paymentService();
        PaymentService obj2 = objectFactory.paymentService();

        Assert.isTrue(obj1 == obj2, "SAME"); // why?
        // Configuration 어노테이션이 붙은 클래스의 매서드(팩토리 매서드) 는 프록시를 사용하기 때문에, 같은 오브젝트가 리턴된다.

        OrderService orderService = beanFactory.getBean(OrderService.class);
        PaymentService paymentServiceBean = beanFactory.getBean(PaymentService.class);
        boolean isSameExRateProvider = orderService.exRateProvider == paymentServiceBean.exRateProvider;

        Assert.isTrue(isSameExRateProvider, "SAME");

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
