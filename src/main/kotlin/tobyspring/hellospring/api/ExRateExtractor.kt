package tobyspring.hellospring.api

import java.math.BigDecimal

fun interface ExRateExtractor {
    fun exRate(response: String): BigDecimal
}