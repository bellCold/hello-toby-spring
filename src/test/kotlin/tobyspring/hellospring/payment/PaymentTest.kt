package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentTest {
    @Test
    fun createPrepared() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock))

        assertThat(payment.convertedAmount).isEqualByComparingTo(BigDecimal.valueOf(10000))
        assertThat(payment.validUntil).isEqualTo(LocalDateTime.now(clock).plusMinutes(30))
    }
}