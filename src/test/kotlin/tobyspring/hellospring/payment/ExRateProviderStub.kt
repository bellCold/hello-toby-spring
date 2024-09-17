package tobyspring.hellospring.payment

import java.math.BigDecimal

class ExRateProviderStub(private val rate: BigDecimal) : ExRateProvider {

    override fun getExRate(currency: String): BigDecimal {
        return rate
    }
}