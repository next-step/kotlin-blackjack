package blackjack.model.result

data class DealerResult(
    val score: Int,
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
