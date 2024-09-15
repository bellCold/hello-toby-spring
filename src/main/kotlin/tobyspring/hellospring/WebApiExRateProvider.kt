package tobyspring.hellospring

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL

class WebApiExRateProvider : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = URL("https://open.er-api.com/v6/latest/${currency}")
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().use {
            val message = it.readLine().toString()
            val objectMapper = ObjectMapper()
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            objectMapper.registerKotlinModule()
            objectMapper.readValue(message, object : TypeReference<List<ExRateData>>() {})
        }.first()

        println("API ExRate: ${data.rates["KRW"]}")

        return data.rates["KRW"] ?: BigDecimal.ZERO
    }
}