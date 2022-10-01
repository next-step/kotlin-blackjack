package blackjack.domain.GameProfit

import java.math.BigDecimal

@JvmInline
value class GameProfit(
    val value: BigDecimal
) {
    operator fun not(): GameProfit {
        return GameProfit(value.negate())
    }

    operator fun plus(other: GameProfit): GameProfit {
        return GameProfit(value.add(other.value))
    }

    companion object {
        val NONE = GameProfit(BigDecimal.ZERO)
        const val SCORE_LIMIT = 21
        const val BLACKJACK_EARNING_RATE = 1.5
    }
}

