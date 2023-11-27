package blackjack.domain.model

class Player(
    val name: PlayerName,
    private var cards: Cards
) {
    fun isPossibleToHit(): Boolean {
        return cards.scores().minBy { it.score } < MAXIMUM_WINNING_SCORE
    }

    fun append(card: Card): Player {
        cards += card

        return this
    }

    companion object {
        private const val MAXIMUM_WINNING_SCORE = 21
    }
}
