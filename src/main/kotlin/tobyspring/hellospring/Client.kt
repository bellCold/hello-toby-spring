package tobyspring.hellospring

import java.math.BigDecimal

fun main() {
    val objectFactory = ObjectFactory()
    val paymentService = objectFactory.paymentService()

    val payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7))
    println(payment)
}
