package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import tobyspring.hellospring.api.ApiTemplate
import tobyspring.hellospring.exrate.CachedExRateProvider
import tobyspring.hellospring.exrate.RestTemplateExRateProvider
import tobyspring.hellospring.exrate.WebApiExRateProvider
import tobyspring.hellospring.payment.ExRateProvider
import tobyspring.hellospring.payment.PaymentService
import java.time.Clock

@Configuration
class PaymentConfig {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider(), clock())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return RestTemplateExRateProvider(restTemplate())
    }

    @Bean
    fun apiTemplate(): ApiTemplate {
        return ApiTemplate()
    }

    @Bean
    fun cachedRateProvider(): ExRateProvider {
        return CachedExRateProvider(exRateProvider())
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}