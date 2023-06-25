package blackjack.domain.result

import blackjack.domain.player.GamePlayer
import blackjack.domain.score.Score

data class GameResult(
    val player: GamePlayer,
    val score: Score,
)
