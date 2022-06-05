package blackjack.domain

class Deck(cards: List<Card>) {
    private val cards: MutableList<Card> = cards.toMutableList()

    init {
        require(cards.size in MINIMUM_CARDS_COUNT..MAXIMUM_CARDS_COUNT) {
            "the deck should have a number of cards from 1 to 52"
        }
    }

    fun draw(count: Int = INITIAL_DRAW_CARD_COUNT): List<Card> {
        require(count in MINIMUM_DRAW_CARD_COUNT..INITIAL_DRAW_CARD_COUNT) {
            "you can draw number of cards only 1 or 2"
        }

        val drawnCards = cards.take(count)
        cards.removeAll(drawnCards)

        return drawnCards
    }

    companion object {
        private const val MINIMUM_CARDS_COUNT = 1
        private const val MAXIMUM_CARDS_COUNT = 52
        private const val INITIAL_DRAW_CARD_COUNT = 2
        private const val MINIMUM_DRAW_CARD_COUNT = 1
    }
}
