package blackjack.domain

import java.math.BigDecimal

data class BetMoney(val amount: BigDecimal) {

    constructor(amount: Int) : this(BigDecimal(amount))

    override fun toString(): String = "$amount"

    companion object {
        val ZERO_BET_MONEY: BetMoney = BetMoney(0)
    }
}
