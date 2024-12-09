package blackjack.domain

class BlackJackGameResults(
    val value: List<BlackJackGameResult>,
) {
    val winningOrLoseResult: WinningOrLoseResult =
        WinningOrLoseResult(dealerScore(), playerWinningOrLose())

    private fun dealerScore(): DealerScore {
        val dealerResult = value.first { it.dealer }
        val playerResults = value.filter { !it.dealer }
        if (isBust(dealerResult.totalValue)) {
            return DealerScore(0, playerResults.size)
        }

        val winningScore = playerResults.count { isDealerWinning(it, dealerResult.totalValue) }
        val loseScore = playerResults.count { isPlayerWinning(it, dealerResult.totalValue) }

        return DealerScore(winningScore, loseScore)
    }

    private fun playerWinningOrLose(): Map<String, Boolean> {
        val dealerResult = value.first { it.dealer }
        val playerResults = value.filter { !it.dealer }
        if (isBust(dealerResult.totalValue)) {
            return playerResults.associate { it.playerName to true }
        }

        return playerResults.associate {
            it.playerName to isPlayerWinning(it, dealerResult.totalValue)
        }
    }

    private fun isDealerWinning(
        playerResult: BlackJackGameResult,
        dealerTotalValue: Int,
    ) = isBust(playerResult.totalValue) || playerResult.totalValue < dealerTotalValue

    private fun isPlayerWinning(
        playerResult: BlackJackGameResult,
        dealerTotalValue: Int,
    ) = !isBust(playerResult.totalValue) && playerResult.totalValue > dealerTotalValue

    private fun isBust(score: Int) = score > BUST_SCORE
}

data class BlackJackGameResult(
    val dealer: Boolean,
    val playerName: String,
    val cards: List<DrawCard>,
    val totalValue: Int,
)

data class WinningOrLoseResult(
    val dealerScore: DealerScore,
    val playerWinningOrLose: Map<String, Boolean>,
)

data class DealerScore(
    val winningScore: Int,
    val loseScore: Int,
)
