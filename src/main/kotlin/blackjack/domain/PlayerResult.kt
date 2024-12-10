package blackjack.domain

import java.math.BigDecimal

data class PlayerResult(
    val name: String,
    val bet: Bet,
    val outcome: PlayerOutcome,
) {
    fun profit(): BigDecimal = outcome.profit(bet)
}
