package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import tobyspring.hellospring.payment.PaymentService
import java.math.BigDecimal

class Client

fun main() {
    val beanFactory = AnnotationConfigApplicationContext(PaymentConfig::class.java)
    val paymentService = beanFactory.getBean(PaymentService::class.java)

    val payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7))
    println(payment)
}
