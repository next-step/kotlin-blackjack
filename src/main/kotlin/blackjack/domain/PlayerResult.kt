package blackjack.domain

enum class PlayerResult {
    WIN, DRAW, LOSE,
    ;

    companion object {
        fun of(playerScore: Int, dealerScore: Int): PlayerResult {
            return when {
                playerScore > dealerScore -> WIN
                playerScore == dealerScore -> DRAW
                else -> LOSE
            }
        }
    }
}
