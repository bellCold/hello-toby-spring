package tobyspring.hellospring.payment

import java.math.BigDecimal
import java.time.LocalDateTime

class Payment(
    val orderId: Long,
    val currency: String,
    val foreignCurrencyAmount: BigDecimal,
    val exRate: BigDecimal,
    val convertedAmount: BigDecimal,
    val validUntil: LocalDateTime
) {
    companion object {
        fun createPrepared(orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal, exRate: BigDecimal, now: LocalDateTime): Payment {
            return Payment(
                orderId = orderId,
                currency = currency,
                foreignCurrencyAmount = foreignCurrencyAmount,
                exRate = exRate,
                convertedAmount = foreignCurrencyAmount.multiply(exRate),
                validUntil = now.plusMinutes(30)
            )
        }
    }

    override fun toString(): String {
        return "Payment(orderId=$orderId, currency='$currency', foreignCurrencyAmount=$foreignCurrencyAmount, exRate=$exRate, convertedAmount=$convertedAmount, validUntil=$validUntil)"
    }
}
