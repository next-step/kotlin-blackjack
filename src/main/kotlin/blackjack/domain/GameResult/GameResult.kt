package blackjack.domain.GameResult

import blackjack.domain.player.Player
import blackjack.domain.score.Score

data class GameResult(
    val player: Player,
    val score: Score,
)
