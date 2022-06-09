package domain.card

class CardDeck {
    private val cards: Cards = initDeck()

    fun draw(): Card {
        return cards.draw()
    }

    private fun initDeck(): Cards {
        val myCards = Cards(mutableListOf())

        Card.Suit.values().forEach { suit ->
            Card.Symbol.values().forEach { symbol -> myCards.add(Card(symbol, suit)) }
        }
        myCards.shuffle()
        return myCards
    }
}
