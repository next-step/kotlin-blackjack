package blackjack

object Cards {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        CardSymbol.values().forEach {
            cards.add(Card(CardShape.SPADE, it))
            cards.add(Card(CardShape.HEART, it))
            cards.add(Card(CardShape.DIAMOND, it))
            cards.add(Card(CardShape.CLOVER, it))
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
