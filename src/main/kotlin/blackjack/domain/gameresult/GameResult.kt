package blackjack.domain.gameresult

enum class GameResult(
    val value: String,
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무")
    ;

    operator fun not(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }

    companion object {
        private const val SCORE_LIMIT = 21

        fun valueOf(playerScore: Int, dealerScore: Int): GameResult {
            if (isDealerWin(playerScore, dealerScore)) {
                return LOSE
            }

            if (isPlayerWin(playerScore, dealerScore)) {
                return WIN
            }

            return DRAW
        }

        private fun isDealerWin(playerScore: Int, dealerScore: Int): Boolean {
            if (playerScore > SCORE_LIMIT) {
                return true
            }

            if (dealerScore > SCORE_LIMIT || dealerScore <= playerScore) {
                return false
            }

            return true
        }

        private fun isPlayerWin(playerScore: Int, dealerScore: Int): Boolean {
            if (playerScore > SCORE_LIMIT) {
                return false
            }

            if (dealerScore > SCORE_LIMIT || dealerScore < playerScore) {
                return true
            }

            return false
        }
    }
}
