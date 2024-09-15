package tobyspring.hellospring.exrate

import tobyspring.hellospring.payment.ExRateProvider
import java.math.BigDecimal

class SimpleExRateProvider : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        return if (currency == "USD") {
            BigDecimal.valueOf(1000)
        } else {
            throw IllegalArgumentException("Invalid currency")
        }
    }
}