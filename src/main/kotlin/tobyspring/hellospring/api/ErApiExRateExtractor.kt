package tobyspring.hellospring.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import tobyspring.hellospring.exrate.ExRateData
import java.math.BigDecimal

class ErApiExRateExtractor : ExRateExtractor {
    override fun exRate(response: String): BigDecimal {
        val objectMapper = ObjectMapper()
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        objectMapper.registerKotlinModule()
        val data = objectMapper.readValue(response, ExRateData::class.java)
        return data.rates["KRW"] ?: BigDecimal.ZERO
    }

}