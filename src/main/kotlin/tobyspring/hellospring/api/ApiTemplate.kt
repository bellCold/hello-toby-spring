package tobyspring.hellospring.api

import com.fasterxml.jackson.core.JsonProcessingException
import java.io.IOException
import java.math.BigDecimal
import java.net.URI
import java.net.URISyntaxException

class ApiTemplate {
    private val apiExecutor: ApiExecutor = HttpClientApiExecutor()
    private val exRateExtractor: ExRateExtractor = ErApiExRateExtractor()

    fun getForExRate(url: String): BigDecimal {
        return this.getForExRate(url, this.apiExecutor, this.exRateExtractor)
    }

    fun getForExRate(url: String, apiExecutor: ApiExecutor): BigDecimal {
        return this.getForExRate(url, apiExecutor, this.exRateExtractor)
    }

    fun getForExRate(url: String, exRateExtractor: ExRateExtractor): BigDecimal {
        return this.getForExRate(url, this.apiExecutor, exRateExtractor)
    }

    fun getForExRate(url: String, apiExecutor: ApiExecutor, exRateExtractor: ExRateExtractor): BigDecimal {
        val uri = try {
            URI(url)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

        val response = try {
            apiExecutor.execute(uri)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

        return try {
            exRateExtractor.exRate(response)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }
}

