package blackjack.domain.game

enum class Rank(val value: String, val order: Int) {
    WON("승", 1),
    LOST("패", 2),
    DRAW("무", 3);

    companion object {
        fun of(playerScore: Int, dealerScore: Int): Rank {
            return when {
                dealerScore > BlackJack.BLACKJACK_MAX_SCORE -> WON
                playerScore > BlackJack.BLACKJACK_MAX_SCORE -> LOST
                playerScore > dealerScore -> WON
                playerScore < dealerScore -> LOST
                else -> DRAW
            }
        }

        fun reverse(ranks: List<Rank>): List<Rank> {
            return ranks.map {
                when (it) {
                    WON -> LOST
                    LOST -> WON
                    DRAW -> DRAW
                }
            }
        }
    }
}
