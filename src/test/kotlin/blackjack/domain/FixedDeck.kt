package blackjack.domain

class FixedDeck : Deck {
    override fun init(): List<Card> {
        return listOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.SPADE, CardNumber.EIGHT))
    }

    override fun hit(): Card {
        return Card(CardSuit.HEART, CardNumber.TWO)
    }
}
