package blackjack.domain

enum class GameResult {
    WIN,
    BUST,
    LOSE,
    PUSH,
    BLACK_JACK,
    ;

    companion object {
        fun fromScores(
            dealerScore: Int,
            playerScore: Int,
        ): GameResult {
            return when {
                dealerScore > Card.MAX_SUM -> WIN // Dealer bust
                playerScore > Card.MAX_SUM -> BUST // Player bust
                dealerScore > playerScore -> LOSE
                playerScore > dealerScore -> WIN
                else -> PUSH
            }
        }
    }
}
