package blackjack.domain.result.game

import blackjack.domain.batting.Amount
import java.math.BigDecimal

data class Profit(
    val value: BigDecimal,
) {

    val negative: Profit
        get() = Profit(value.negate())

    operator fun plus(other: Profit): Profit = value.plus(other.value).let(::Profit)

    companion object {
        fun of(betAmount: Amount, payoutAmount: Amount): Profit =
            payoutAmount.value.minus(betAmount.value).let(::Profit)
    }
}
