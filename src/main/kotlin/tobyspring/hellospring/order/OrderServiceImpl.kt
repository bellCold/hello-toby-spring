package tobyspring.hellospring.order

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository) : OrderService {

    override fun createOrder(no: String, total: BigDecimal): Order {
        val order = Order(no, total)

        orderRepository.save(order)
        return order
    }

    override fun createOrders(request: List<OrderRequest>): List<Order> {
        return request.map { createOrder(it.no, it.total) }
    }
}