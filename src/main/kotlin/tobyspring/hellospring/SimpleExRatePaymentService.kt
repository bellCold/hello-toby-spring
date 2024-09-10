package tobyspring.hellospring

import java.math.BigDecimal

class SimpleExRatePaymentService : PaymentService() {
    override fun getExRate(currency: String): BigDecimal {
        return if (currency == "USD") {
            BigDecimal.valueOf(1000)
        } else {
            throw IllegalArgumentException("Invalid currency")
        }
    }
}