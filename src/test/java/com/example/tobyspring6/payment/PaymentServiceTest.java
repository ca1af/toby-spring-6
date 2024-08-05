package com.example.tobyspring6.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 매소드가 요구사항 3 가지를 잘 충족했는지 검증한다.")
    void convertedAmount() throws IOException {
        // arrange
        getPayment(BigDecimal.valueOf(500), BigDecimal.valueOf(5000));
    }

    @Test
    void validUntil() throws IOException {
        // 원화 환산 금액 유효시간을 잘 계산한다 - act3
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until 이 prepare() 30분 뒤로 설정되었는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expected = now.plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expected);
    }

    private void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보를 가져온다 - act1
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 환율정보를 바탕으로 원화 환산 - act2
        assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
    }
}