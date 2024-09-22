package tobyspring.hellospring.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import tobyspring.hellospring.OrderConfig
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OrderConfig::class])
class OrderServiceSpringTest(@Autowired private val orderService: OrderService) {

    @Test
    fun createOrder() {
        val order = orderService.createOrder("0100", BigDecimal.ONE)

        assertThat(order?.id).isGreaterThan(0)
    }
}