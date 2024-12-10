package blackjack.domain

import java.math.BigDecimal
import java.math.RoundingMode

enum class PlayerOutcome(
    private val ratio: BigDecimal,
) {
    WIN(BigDecimal(1.0)),
    LOSE(BigDecimal(-1.0)),
    DRAW(BigDecimal.ZERO),
    ;

    fun profit(bet: Bet): BigDecimal = bet.value.times(ratio).round()

    companion object {
        fun of(
            player: Player,
            dealer: Dealer,
        ): PlayerOutcome =
            when {
                player.isBusted -> LOSE
                dealer.isBusted -> WIN
                player.isBlackjack && !dealer.isBlackjack -> WIN
                player.pushes(dealer) -> DRAW
                player.beats(dealer) -> WIN
                else -> LOSE
            }
    }
}

private fun BigDecimal.round(): BigDecimal = setScale(0, RoundingMode.HALF_UP)
