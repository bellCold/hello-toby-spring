package tobyspring.hellospring.order

import java.math.BigDecimal

class Order(
    val id: Long? = 0,
    val no: String,
    val total: BigDecimal
) {
    constructor(no: String, total: BigDecimal) : this(
        id = null,
        no = no,
        total = total
    )

    override fun toString(): String {
        return "Order(id=$id, no='$no', total=$total)"
    }
}