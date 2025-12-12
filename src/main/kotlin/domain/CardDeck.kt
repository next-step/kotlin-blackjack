package domain

class CardDeck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        reset()
    }

    private fun reset() {
        cards.clear()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("덱에 카드가 없습니다")
        }
        return cards.removeAt(cards.size - 1)
    }

    fun remainingCards(): Int = cards.size

    fun isEmpty(): Boolean = cards.isEmpty()
}
