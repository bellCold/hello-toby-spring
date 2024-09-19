package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import tobyspring.hellospring.order.OrderService
import java.math.BigDecimal

class OrderClient

fun main() {
    val beanFactory = AnnotationConfigApplicationContext(OrderConfig::class.java)
    val service = beanFactory.getBean(OrderService::class.java)

    val order = service.createOrder("0100", BigDecimal(100))
    println(order)
}