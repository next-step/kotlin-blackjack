package blackjack.domain

class MockCardDeck : Deck {
    override fun draw(): Card {
        return Card(Card.CardPattern.CLUBS, Card.CardDisplayValue.EIGHT)
    }
}