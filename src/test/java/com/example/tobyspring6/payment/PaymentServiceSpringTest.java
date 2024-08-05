package com.example.tobyspring6.payment;

import com.example.tobyspring6.TestPaymentConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;
    @Autowired Clock clock;

    @Test
    @DisplayName("prepare 매소드가 요구사항 3 가지를 잘 충족했는지 검증한다.")
    void prepare() throws IOException {
        // exRate : 1000
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualTo(valueOf(10_000));

        // exRate : 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualTo(valueOf(5_000));

    }

    @Test
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until 이 prepare() 30분 뒤로 설정되었는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expected = now.plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expected);
    }
}