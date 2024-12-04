package blackjack.domain

import blackjack.domain.participant.Player

data class GameResult(
    val dealerName: String,
    val playersResult: List<PlayerGameResult>,
) {
    val dealerWinCount = playersResult.filter { !it.isWin }.size
    val dealerLoseCount = playersResult.size - dealerWinCount
}

data class PlayerGameResult(
    val player: Player,
    val isWin: Boolean,
)
