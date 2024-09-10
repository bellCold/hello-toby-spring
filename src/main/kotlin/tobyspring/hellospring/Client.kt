package tobyspring.hellospring

import java.math.BigDecimal

fun main() {
//    val paymentService = WebApiExRatePaymentService()
    val paymentService = SimpleExRatePaymentService()
    val payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7))
    println(payment)
}