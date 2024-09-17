package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.Test

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    fun prepare() {
        val paymentService = PaymentService(ExRateProviderStub(BigDecimal.valueOf(500)))

        val payment = paymentService.prepare(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN
        )

        assertThat(payment.exRate).isEqualByComparingTo(BigDecimal.valueOf(500))
        assertThat(payment.convertedAmount).isEqualByComparingTo(BigDecimal.valueOf(5000))
        assertThat(payment.validUntil).isAfter(LocalDateTime.now())
    }

}