package tobyspring.hellospring.order

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue
    val id: Long?,
    @Column(unique = true)
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