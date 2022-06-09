package domain.card

class CardDeck {
    lateinit var cards: Cards

    init {
        Card.Suit.values().forEach { suit ->
            Card.Symbol.values().forEach { symbol -> cards.add(Card(symbol, suit)) }
        }
    }

    fun drawCard(): Card {
        return cards.draw().apply {
            cards.remove(this)
        }
    }

    fun shuffle(){
        cards.shuffle()
    }
}
