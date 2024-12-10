package study.blackjack.model

/**
 * @author 이상준
 */

enum class Match {
    WIN,
    LOSE,
    PUSH,
    WAIT,
    ;

    companion object {
        fun of(
            playerScore: Int,
            dealerScore: Int,
        ): Match {
            return when {
                blackjackCheck(playerScore) && blackjackCheck(dealerScore) -> PUSH
                blackjackCheck(playerScore) -> WIN
                blackjackCheck(dealerScore) -> LOSE
                bustCheck(playerScore) -> LOSE
                bustCheck(dealerScore) -> WIN
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> PUSH
            }
        }

        const val BLACKJACK = 21

        private fun blackjackCheck(score: Int): Boolean {
            return score == BLACKJACK
        }

        fun bustCheck(score: Int): Boolean {
            return score > BLACKJACK
        }
    }
}
