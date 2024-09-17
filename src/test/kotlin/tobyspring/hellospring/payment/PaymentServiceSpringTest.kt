package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import tobyspring.hellospring.TestObjectFactory
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.Test

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestObjectFactory::class])
class PaymentServiceSpringTest(@Autowired val paymentService: PaymentService) {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    fun prepare() {
        val payment = paymentService.prepare(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN
        )

        assertThat(payment.exRate).isEqualByComparingTo(BigDecimal.valueOf(1000))
        assertThat(payment.convertedAmount).isEqualByComparingTo(BigDecimal.valueOf(10000))
        assertThat(payment.validUntil).isAfter(LocalDateTime.now())
    }

}