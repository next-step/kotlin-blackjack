package blackjack.model.result

import blackjack.model.player.BlackjackScore

data class DealerResult(
    val score: BlackjackScore,
    val winningCount: Int,
    val drawingCount: Int,
    val losingCount: Int,
) {
    companion object {
        fun of(playerResults: List<PlayableResult>, score: BlackjackScore): DealerResult {
            return DealerResult(
                score = score,
                winningCount = playerResults.count { it == PlayableResult.WIN },
                drawingCount = playerResults.count { it == PlayableResult.DRAW },
                losingCount = playerResults.count { it == PlayableResult.LOSE }
            )
        }
    }
}
