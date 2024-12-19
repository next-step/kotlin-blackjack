package blackjack.domain

enum class GameResult {
    WIN,
    BUST,
    LOSE,
    PUSH,
    BLACK_JACK,
    ;

    companion object {
        fun getGameResultsWith(
            isPlayerBlackJackInitially: Boolean,
            isDealerBlackJackInitially: Boolean,
            dealerCardSum: Int,
            playerCardSum: Int,
        ): GameResult {
            return when {
                isPlayerBlackJackInitially && isDealerBlackJackInitially.not() -> BLACK_JACK
                isPlayerBlackJackInitially && isDealerBlackJackInitially -> PUSH
                else -> fromScores(dealerCardSum, playerCardSum)
            }
        }

        private fun fromScores(
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
