package blackjack

class FixedDeck : Deck {
    override fun hit(): Card {
        return Card(CardSuit.HEART, CardNumber.TWO)
    }
}
