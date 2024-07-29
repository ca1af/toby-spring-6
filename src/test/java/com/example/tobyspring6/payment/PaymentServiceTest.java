package com.example.tobyspring6.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 매소드가 요구사항 3 가지를 잘 충족했는지 검증한다.")
    void prepare() throws IOException {
        // arrange
        getPayment(BigDecimal.valueOf(500), BigDecimal.valueOf(5000));

        // 원화 환산 금액 유효시간을 잘 계산한다 - act3
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보를 가져온다 - act1
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 환율정보를 바탕으로 원화 환산 - act2
        assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
    }
}