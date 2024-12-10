package blackjack.domain

import java.math.BigDecimal

data class GameResult(
    val playerResults: List<PlayerResult>,
) {
    fun dealerProfit(): BigDecimal =
        playerResults
            .map { it.profit() }
            .fold(BigDecimal.ZERO, BigDecimal::add)
            .negate()
}
