package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.test.Test

class PaymentServiceTest {

    private lateinit var clock: Clock

    @BeforeEach
    fun setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"))
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    fun convertedAmount() {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5000), clock)
    }

    @Test
    fun validUntil() {
        val paymentService = PaymentService(ExRateProviderStub(BigDecimal.valueOf(1000)), clock)

        val payment = paymentService.prepare(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN
        )

        val now = LocalDateTime.now(clock)
        val expectedValidUntil = now.plusMinutes(30)

        assertThat(payment.validUntil).isEqualTo(expectedValidUntil)
    }

    private fun testAmount(exRate: BigDecimal, convertedAmount: BigDecimal, clock: Clock) {
        val paymentService = PaymentService(ExRateProviderStub(exRate), clock)

        val payment = paymentService.prepare(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN
        )

        assertThat(payment.exRate).isEqualByComparingTo(exRate)
        assertThat(payment.convertedAmount).isEqualByComparingTo(convertedAmount)
    }
}