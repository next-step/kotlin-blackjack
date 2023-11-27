package blackjack.domain.model

class Player(
    private val name: String,
    private var cards: Cards
) {
    fun isPossibleToDraw(): Boolean {
        return cards.scores().minBy { it.score } < MAXIMUM_WINNING_SCORE
    }

    companion object {
        private const val MAXIMUM_WINNING_SCORE = 21
    }
}
