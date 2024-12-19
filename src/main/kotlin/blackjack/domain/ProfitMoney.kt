package blackjack.domain

import java.math.BigDecimal

class ProfitMoney {
    private var current: BigDecimal = BigDecimal.ZERO

    fun getCurrentProfit(): BigDecimal {
        return current
    }

    fun set(amount: BigDecimal) {
        current = amount
    }
}
