package blackjack.dto

import blackjack.model.PlayerResultStatus

data class GameResult(
    val point: Int,
    val playerResultStatus: PlayerResultStatus,
)
