package tobyspring.hellospring.order

import java.math.BigDecimal

interface OrderService {
    fun createOrder(no: String, total: BigDecimal): Order
    fun createOrders(request: List<OrderRequest>): List<Order>
}