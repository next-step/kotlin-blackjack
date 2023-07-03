package blackjack

class BlackjackShoe(deck: Int = DEFAULT_DECK_OF_CARDS) {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        require(deck in 1..MAX_DECK_OF_CARDS)
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.addAll(List(deck) { Card(suit, rank) })
            }
        }

        cards.shuffle()
    }

    fun draw(): Card {
        return cards.removeAt(0)
    }

    companion object {
        private const val DEFAULT_DECK_OF_CARDS = 6
        private const val MAX_DECK_OF_CARDS = 8
    }
}
