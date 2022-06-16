package blackjack

class BlackjackCardShuffle : CardShuffle {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        CardSymbol.values().forEach {
            cards.add(Card(CardShape.SPADE, it))
            cards.add(Card(CardShape.HEART, it))
            cards.add(Card(CardShape.DIAMOND, it))
            cards.add(Card(CardShape.CLOVER, it))
        }
    }

    override fun getCards(): List<Card> {
        return cards
            .shuffled()
            .toList()
    }
}
