package blackjack.dto

class PlayerResult(
    val playerName: String,
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int
) {
    fun getResult(): String {
        return when {
            winCount > WIN_COMPARE_COUNT -> WIN_RETURN_VALUE
            else -> LOSE_RETURN_VALUE
        }
    }

    companion object {
        private const val WIN_COMPARE_COUNT = 0
        private const val WIN_RETURN_VALUE = "승"
        private const val LOSE_RETURN_VALUE = "패"
    }
}