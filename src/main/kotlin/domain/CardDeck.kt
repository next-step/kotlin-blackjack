package domain

class CardDeck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.entries) {
            for (value in CardValue.entries) {
                cards.add(Card(suit, value))
            }
        }
        cards.shuffle()
    }

    fun drawCard(): Card {
        check(cards.isNotEmpty()) { "카드가 더이상 없습니다." }
        return cards.removeAt(cards.size - 1)
    }
}
