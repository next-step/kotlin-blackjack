package blackjack.domain

import kotlin.math.roundToInt

data class PlayerGameResult(
    val name: String,
    val betAmount: BetAmount,
    val result: PlayerWinLoseResult,
) : CalculateEarnAmount {
    override fun getEarnAmount(): Int {
        return (betAmount.amount * result.odds).roundToInt()
    }
}
