package blackjack.domain

enum class PlayerRank(val value: String) {
    WON("승"),
    LOST("패"),
    DRAW("무");

    companion object {
        fun getValues(playerScore: Int, dealerScore: Int): PlayerRank {
            return when {
                dealerScore > BlackJack.BLACKJACK_MAX_SCORE -> WON
                playerScore > BlackJack.BLACKJACK_MAX_SCORE -> LOST
                playerScore > dealerScore -> WON
                playerScore < dealerScore -> LOST
                else -> DRAW
            }
        }
    }
}
