package tobyspring.hellospring

import java.math.BigDecimal
import java.time.LocalDateTime

class CachedExRateProvider(private val target: ExRateProvider) : ExRateProvider {
    private var cachedExRate: BigDecimal? = null
    private var cacheExpiryTime: LocalDateTime? = null

    override fun getExRate(currency: String): BigDecimal {
        if (cachedExRate == null || cacheExpiryTime!!.isBefore(LocalDateTime.now())) {
            cachedExRate = target.getExRate(currency)
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3)
            println("Cache Update")
        }

        return cachedExRate ?: BigDecimal.ZERO
    }
}