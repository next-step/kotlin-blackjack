package blackjack.domain.score

import blackjack.domain.player.Player

data class GameResult(
    val player: Player,
    val score: Score,
)
