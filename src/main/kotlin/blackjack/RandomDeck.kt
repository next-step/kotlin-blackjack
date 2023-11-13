package blackjack

data class RandomDeck(
    val cards: List<Card> = listOf()
) : Deck {
    override fun hit(): Card {
        return Card(CardSuit.HEART, CardNumber.TWO)
    }
}
