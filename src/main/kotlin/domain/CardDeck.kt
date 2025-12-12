package domain

class CardDeck {
    val cards: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.entries) {
            for (value in CardValue.entries) {
                cards.add(Card(suit, value))
            }
        }
        cards.shuffle()
    }

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("카드가 더이상 없습니다.")
        }
        return cards.removeAt(0)
    }
}
