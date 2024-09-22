package tobyspring.hellospring.order

import java.math.BigDecimal

data class OrderRequest(val no: String, val total: BigDecimal)
