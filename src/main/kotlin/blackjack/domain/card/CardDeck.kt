package blackjack.domain.card

class CardDeck {
    lateinit var cards: MutableList<Card>

    init {
        setNewCards()
    }

    fun peekCard(index: Int): Card {
        if (cards.isEmpty()) setNewCards()
        return cards.removeAt(index)
    }

    private fun setNewCards() {
        cards = CardNumber.values().flatMap { number ->
            CardSymbol.values().map { symbol ->
                Card(number, symbol)
            }
        }.toMutableList()
    }
}
