package blackjack.model.result

import blackjack.model.playable.BlackjackScore
import blackjack.model.playable.PlayableResult

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
                winningCount = playerResults.count { it == PlayableResult.LOSE },
                drawingCount = playerResults.count { it == PlayableResult.DRAW },
                losingCount = playerResults.count { it == PlayableResult.WIN }
            )
        }
    }
}
