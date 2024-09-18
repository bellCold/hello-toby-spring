package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import tobyspring.hellospring.data.OrderRepository
import tobyspring.hellospring.order.Order
import java.math.BigDecimal

class DataClient

fun main() {
    val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
    val repository = beanFactory.getBean(OrderRepository::class.java)
    val transactionManager = beanFactory.getBean(JpaTransactionManager::class.java)

    try {
        TransactionTemplate(transactionManager).execute {
            val order = Order("100", BigDecimal.TEN)
            repository.save(order)
            println(order)

            val order2 = Order("100", BigDecimal.ONE)
            repository.save(order2)
            return@execute null
        }
    } catch (e: DataIntegrityViolationException) {
        println("주문번호 중복 복구 작업")
    }
}