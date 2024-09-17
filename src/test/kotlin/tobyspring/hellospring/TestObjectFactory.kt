package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tobyspring.hellospring.payment.ExRateProvider
import tobyspring.hellospring.payment.ExRateProviderStub
import tobyspring.hellospring.payment.PaymentService
import java.math.BigDecimal

@Configuration
class TestObjectFactory {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return ExRateProviderStub(BigDecimal.valueOf(1000))
    }
}