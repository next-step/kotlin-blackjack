package blackjack.domain

import blackjack.domain.participant.GameResult
import blackjack.domain.participant.PlayerName

data class PlayerGameResult(
    val playerName: PlayerName,
    val gameResult: GameResult,
)
