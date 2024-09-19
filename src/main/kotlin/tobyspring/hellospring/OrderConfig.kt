package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.orm.jpa.JpaTransactionManager
import tobyspring.hellospring.data.JpaOrderRepository
import tobyspring.hellospring.order.OrderRepository
import tobyspring.hellospring.order.OrderService

@Configuration
@Import(DataConfig::class)
class OrderConfig {
    @Bean
    fun orderService(transactionManager: JpaTransactionManager): OrderService {
        return OrderService(orderRepository(), transactionManager)
    }

    @Bean
    fun orderRepository(): OrderRepository {
        return JpaOrderRepository()
    }
}