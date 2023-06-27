package blackjack.domain

enum class PlayerRank(val playerResult: String, val dealerResult: String) {
    WON("승", "패"),
    LOST("패", "승"),
    DRAW("무", "무");

    companion object {
        fun getValues(playerScore: Int, dealerScore: Int): PlayerRank {

            if (dealerScore > BlackJack.BLACKJACK_MAX_SCORE) {
                return WON
            }


            if (playerScore > BlackJack.BLACKJACK_MAX_SCORE) {
                return LOST
            }

            return when {
                playerScore > dealerScore -> WON
                playerScore < dealerScore -> LOST
                else -> DRAW
            }
        }
    }
}
