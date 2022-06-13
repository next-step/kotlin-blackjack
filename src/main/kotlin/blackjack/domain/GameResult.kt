package blackjack.domain

enum class GameResult {
    PLAYER_WON,
    PLAYER_BLACKJACK_WON,
    DEALER_WON,
    DRAW;

    companion object {

        fun from(dealerScore: Score, playerScore: Score): GameResult =
            when {
                dealerScore.isBust -> PLAYER_WON
                playerScore.isBust -> DEALER_WON
                playerScore.isBlackjack && dealerScore.isBlackjack -> DRAW
                playerScore.isBlackjack -> PLAYER_BLACKJACK_WON
                playerScore > dealerScore -> PLAYER_WON
                dealerScore > playerScore -> DEALER_WON
                else -> DRAW
            }
    }
}
