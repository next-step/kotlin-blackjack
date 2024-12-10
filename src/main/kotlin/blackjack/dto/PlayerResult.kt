package blackjack.dto

class PlayerResult(
    val isPlayer: Boolean,
    val playerName: String,
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int
) {
    fun getResult(): String {
        return when {
            isPlayer -> playerResult()
            else -> dealerResult()
        }
    }

    private fun dealerResult(): String {
        return "${playerName}: ${winCount}승 ${drawCount}무 ${loseCount}패"
    }

    private fun playerResult(): String {
        return "${playerName}: ${if (winCount> 0) "승" else if(drawCount > 0) "무" else "패"}"
    }
}