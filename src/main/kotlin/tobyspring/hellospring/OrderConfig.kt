package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.PlatformTransactionManager
import tobyspring.hellospring.data.JdbcOrderRepository
import tobyspring.hellospring.order.OrderRepository
import tobyspring.hellospring.order.OrderService
import tobyspring.hellospring.order.OrderServiceImpl
import tobyspring.hellospring.order.OrderServiceTxProxy
import javax.sql.DataSource

@Configuration
@Import(DataConfig::class)
class OrderConfig {
    @Bean
    fun orderService(
        transactionManager: PlatformTransactionManager,
        orderRepository: OrderRepository
    ): OrderService {
        return OrderServiceTxProxy(
            OrderServiceImpl(orderRepository),
            transactionManager
        )
    }

    @Bean
    fun orderRepository(dataSource: DataSource): OrderRepository {
        return JdbcOrderRepository(dataSource)
    }
}