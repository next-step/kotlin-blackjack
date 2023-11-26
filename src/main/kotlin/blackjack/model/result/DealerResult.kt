package blackjack.model.result

import blackjack.model.playable.BlackjackScore

data class DealerResult(
    val score: BlackjackScore,
    val winningCount: Int,
    val drawingCount: Int,
    val losingCount: Int,
) {
    companion object {
        fun a(): DealerResult {
            TODO("Not yet implemented")
        }
    }
}
