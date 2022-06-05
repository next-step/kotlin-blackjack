package blackjack.domain

class MockCardDeck(private val card: Card) : Deck {
    override fun draw(): Card {
        return card
    }
}
