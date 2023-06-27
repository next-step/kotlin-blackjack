package blackjack.domain

enum class PlayerRank(val playerResult: String, val dealerResult: String) {
    WON("승", "패"),
    LOST("패", "승"),
    DRAW("무", "무");

    companion object {
        fun getValues(playerScore: Int, dealerScore: Int): PlayerRank {
            return when {
                playerScore > dealerScore -> WON
                playerScore < dealerScore -> LOST
                else -> DRAW
            }
        }
    }
}
