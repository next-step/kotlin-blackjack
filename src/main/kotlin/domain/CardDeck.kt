package domain

class CardDeck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        reset()
    }

    private fun reset() {
        cards.clear()
        for (suit in Suit.entries) {
            for (rank in Rank.entries) {
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun drawCard(): Card {
        check(cards.isNotEmpty()) { "덱에 카드가 없습니다" }
        return cards.removeLast()
    }
}
