package blackjack.model.result

data class DealerResult(
    val score: Int,
    val dealerWinningCount: Int,
    val dealerDrawingCount: Int,
    val dealerLosingCount: Int,
)
