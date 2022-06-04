package blackjack.domain

class Deck(cards: List<Card>) {
    private val cards: MutableList<Card> = cards.shuffled().toMutableList()

    init {
        require(cards.size <= TOTAL_CARDS_COUNT) {
            "maximum cards count could not be greater than 52"
        }
    }

    fun draw(count: Int = INITIAL_DRAW_CARD_COUNT): List<Card> {
        require(count in MINIMUM_DRAW_CARD_COUNT..INITIAL_DRAW_CARD_COUNT) {
            "you can draw number of cards only 1 or 2"
        }

        val drawnCards = cards.take(2)
        cards.removeAll(drawnCards)

        return drawnCards
    }

    companion object {
        private const val TOTAL_CARDS_COUNT = 52
        private const val INITIAL_DRAW_CARD_COUNT = 2
        private const val MINIMUM_DRAW_CARD_COUNT = 1
    }
}
