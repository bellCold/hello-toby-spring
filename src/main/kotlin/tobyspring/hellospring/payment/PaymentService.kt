package tobyspring.hellospring.payment

import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

class PaymentService(private val exRateProvider: ExRateProvider, private val clock: Clock) {

    fun prepare(orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal): Payment {
        val exRate = exRateProvider.getExRate(currency)

        return Payment.createPrepared(
            orderId = orderId,
            currency = currency,
            foreignCurrencyAmount = foreignCurrencyAmount,
            exRate = exRate,
            now = LocalDateTime.now(clock)
        )
    }
}