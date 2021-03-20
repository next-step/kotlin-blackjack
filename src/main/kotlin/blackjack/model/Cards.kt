package blackjack.model

class Cards(private val cards: List<Card> = firstDraw()) : List<Card> by cards {
    override fun toString(): String {
        return cards.joinToString(separator = ", ") { "$it" }
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2

        private fun firstDraw() = (1..INITIAL_DRAW_COUNT).map { Card.get() }
    }
}
