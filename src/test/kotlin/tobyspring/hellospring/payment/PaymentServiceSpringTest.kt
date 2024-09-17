package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import tobyspring.hellospring.TestPaymentConfig
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime
import kotlin.test.Test

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestPaymentConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class PaymentServiceSpringTest(private val paymentService: PaymentService, private val clock: Clock) {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    fun convertedAmount() {
        val payment = paymentService.prepare(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN
        )

        assertThat(payment.exRate).isEqualByComparingTo(BigDecimal.valueOf(1000))
        assertThat(payment.convertedAmount).isEqualByComparingTo(BigDecimal.valueOf(10000))
    }

    @Test
    fun validUntil() {
        val payment = paymentService.prepare(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN
        )

        val now = LocalDateTime.now(clock)
        val expectedValidUntil = now.plusMinutes(30)

        assertThat(payment.validUntil).isEqualTo(expectedValidUntil)
    }

}