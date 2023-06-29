package blackjack.domain

import java.math.BigDecimal

enum class GameResult {
    WIN,
    TIE,
    LOSE,
    ;

    fun calculateProfit(betAmount: BigDecimal, profitRate: BigDecimal): Profit {
        return when (this) {
            WIN -> Profit(betAmount * profitRate)
            TIE -> Profit.ZERO
            LOSE -> Profit(-betAmount)
        }
    }
}
