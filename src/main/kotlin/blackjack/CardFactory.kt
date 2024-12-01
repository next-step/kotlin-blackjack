package blackjack

object CardFactory {
    fun from(
        cardNumber: CardNumber,
        suit: Suit,
    ): Card = Card(cardNumber, suit)

    fun all(): List<Card> {
        return Suit.entries.flatMap { suit ->
            CardNumberFactory.all().map { cardNumber ->
                Card(cardNumber, suit)
            }
        }
    }
}
