package blackjack.domain

import blackjack.domain.participant.BetAmount
import blackjack.domain.participant.GameResult
import blackjack.domain.participant.PlayerName
import kotlin.math.roundToInt

data class PlayerGameResult(
    val playerName: PlayerName,
    val betAmount: BetAmount,
    val gameResult: GameResult,
) {
    val yield: Int = (betAmount.value * gameResult.magnification).roundToInt()
}
