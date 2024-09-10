package tobyspring.hellospring

class ObjectFactory {
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider())
    }

    private fun exRateProvider(): ExRateProvider {
        return WebApiExRateProvider()
    }
}