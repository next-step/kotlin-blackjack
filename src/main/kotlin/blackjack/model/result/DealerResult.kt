package blackjack.model.result

import blackjack.model.player.BlackjackScore

data class DealerResult(
    val score: BlackjackScore,
    val winningCount: Int,
    val drawingCount: Int,
    val losingCount: Int,
)
