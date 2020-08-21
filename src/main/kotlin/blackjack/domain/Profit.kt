package blackjack.domain

import java.math.BigDecimal
import java.math.RoundingMode

data class Profit(val amount: BigDecimal) {

    constructor(amount: Int) : this(BigDecimal(amount))

    fun earnAsMuchAsStake(): Profit = Profit(amount)

    fun loseAll(): Profit = Profit(-amount)

    fun gainNothing(): Profit = ZERO_PROFIT

    fun earnWhenIsBlackJack(): Profit =
        Profit(amount.multiply(BigDecimal(0.5)).setScale(0, RoundingMode.FLOOR))

    override fun toString(): String = "$amount"

    companion object {
        val ZERO_PROFIT: Profit = Profit(0)
    }
}
