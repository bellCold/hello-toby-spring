package tobyspring.hellospring.order

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

class OrderServiceTxProxy(
    private val target: OrderService,
    private val transactionManager: PlatformTransactionManager
) : OrderService {
    override fun createOrder(no: String, total: BigDecimal): Order {
        return TransactionTemplate(transactionManager).execute {
            return@execute target.createOrder(no, total)
        } ?: throw IllegalStateException("some error")
    }

    override fun createOrders(request: List<OrderRequest>): List<Order> {
        return TransactionTemplate(transactionManager).execute {
            return@execute target.createOrders(request)
        } ?: emptyList()
    }
}