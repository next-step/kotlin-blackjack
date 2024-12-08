package blackjack.domain

class BlackJackGameResults(
    val value: List<BlackJackGameResult>,
) {
    private val dealerResult = value.first { it.dealer }
    private val playerResults = value.filter { !it.dealer }

    val dealerScore = dealerScore()
    val playerWinningOrLose = playerWinningOrLose()

    private fun dealerScore(): DealerScore {
        if (isBust(dealerResult.totalValue)) {
            return DealerScore(0, playerResults.size)
        }

        val winningScore = playerResults.count { isDealerWinning(it) }
        val loseScore = playerResults.count { isPlayerWinning(it) }

        return DealerScore(winningScore, loseScore)
    }

    private fun playerWinningOrLose(): Map<String, Boolean> {
        if (isBust(dealerResult.totalValue)) {
            return playerResults.associate { it.playerName to true }
        }

        return playerResults.associate {
            it.playerName to isPlayerWinning(it)
        }
    }

    private fun isDealerWinning(playerResult: BlackJackGameResult) =
        isBust(playerResult.totalValue) || playerResult.totalValue < dealerResult.totalValue

    private fun isPlayerWinning(playerResult: BlackJackGameResult) =
        !isBust(playerResult.totalValue) && playerResult.totalValue > dealerResult.totalValue

    private fun isBust(score: Int) = score > BUST_SCORE
}

data class BlackJackGameResult(
    val dealer: Boolean,
    val playerName: String,
    val cards: List<DrawCard>,
    val totalValue: Int,
)

data class DealerScore(
    val winningScore: Int,
    val loseScore: Int,
)
