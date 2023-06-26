package blackjack.domain

class Cards {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        CardSymbol.values().forEach {
            cards.add(Card(CardShape.SPADE, it))
            cards.add(Card(CardShape.CLOVER, it))
            cards.add(Card(CardShape.HEART, it))
            cards.add(Card(CardShape.DIAMOND, it))
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun takeCards(count: Int): List<Card> {
        cards.shuffle()
        val takenCards = cards.take(count)
        cards.removeAll(takenCards)
        return takenCards
    }
}
