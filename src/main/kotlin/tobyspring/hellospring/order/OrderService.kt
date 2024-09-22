package tobyspring.hellospring.order

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val transactionManager: PlatformTransactionManager
) {
    fun createOrder(no: String, total: BigDecimal): Order {
        val order = Order(no, total)

        return TransactionTemplate(transactionManager).execute {
            orderRepository.save(order)
            return@execute order
        } ?: throw IllegalStateException("error")
    }

    fun createOrders(request: List<OrderRequest>): List<Order> {
        return request.map { createOrder(it.no, it.total) }
    }
}