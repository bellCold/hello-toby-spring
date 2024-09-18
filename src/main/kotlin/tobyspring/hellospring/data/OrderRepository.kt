package tobyspring.hellospring.data

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import tobyspring.hellospring.order.Order

class OrderRepository {
    @PersistenceContext
    private lateinit var em: EntityManager

    fun save(order: Order) {
        em.persist(order)
    }
}