package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tobyspring.hellospring.exrate.CachedExRateProvider
import tobyspring.hellospring.payment.ExRateProvider
import tobyspring.hellospring.exrate.WebApiExRateProvider
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
        return WebApiExRateProvider()
    }

    @Bean
    fun cachedRateProvider(): ExRateProvider {
        return CachedExRateProvider(exRateProvider())
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }
}