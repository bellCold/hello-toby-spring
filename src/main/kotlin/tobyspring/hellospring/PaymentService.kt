package tobyspring.hellospring

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime

class PaymentService {

    fun prepare(orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal): Payment {
        val exRate = getExRate(currency)
        val convertedAmount = foreignCurrencyAmount.multiply(exRate)
        val validUntil = LocalDateTime.now().plusMinutes(30)

        return Payment(
            orderId = orderId,
            currency = currency,
            foreignCurrencyAmount = foreignCurrencyAmount,
            exRate = exRate,
            convertedAmount = convertedAmount,
            validUntil = validUntil
        )
    }

    private fun getExRate(currency: String): BigDecimal? {
        val url = URL("https://open.er-api.com/v6/latest/${currency}")
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().use {
            val message = it.readLine().toString()
            val objectMapper = ObjectMapper()
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            objectMapper.registerKotlinModule()
            objectMapper.readValue(message, object : TypeReference<List<ExRateData>>() {})
        }.first()

        val exRate = data.rates["KRW"]
        return exRate
    }
}

fun main() {
    val paymentService = PaymentService()
    val payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7))
    println(payment)
}