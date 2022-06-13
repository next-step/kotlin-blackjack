package blackjack.domain

class MockCardDeck(private val card: Card) : Deck {
    override fun draw(): Card {
        return card
    }
}

class MockCardListDeck(private val cards: List<Card>) : Deck {

    private val _cards = cards.toMutableList()
    override fun draw(): Card {
        val card = _cards.removeAt(0)
        _cards.add(card)
        return card
    }
}
