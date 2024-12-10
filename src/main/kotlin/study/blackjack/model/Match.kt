package study.blackjack.model

/**
 * @author 이상준
 */

enum class Match(val operation: (Int) -> Double) {
    BLACKJACK({ profit -> profit * 1.5 }),
    WIN({ profit -> profit.toDouble() }),
    LOSE({ profit -> -profit.toDouble() }),
    PUSH({ profit -> profit.toDouble() }),
    WAIT({ profit -> profit.toDouble() }),
    ;

    companion object {
        fun of(
            playerScore: Int,
            dealerScore: Int,
        ): Match {
            return when {
                blackjackCheck(playerScore) && blackjackCheck(dealerScore) -> PUSH
                blackjackCheck(playerScore) -> BLACKJACK
                blackjackCheck(dealerScore) -> LOSE
                bustCheck(playerScore) -> LOSE
                bustCheck(dealerScore) -> WIN
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> PUSH
            }
        }

        fun bustCheck(score: Int): Boolean {
            return score > BLACKJACK_NUMBER
        }

        private fun blackjackCheck(score: Int): Boolean {
            return score == BLACKJACK_NUMBER
        }

        const val BLACKJACK_NUMBER = 21
    }
}
