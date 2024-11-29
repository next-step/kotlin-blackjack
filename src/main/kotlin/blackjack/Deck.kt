package blackjack

class Deck(private val cards: MutableList<Card> = CARDS.toMutableList()) {
    fun draw(): Card = cards.removeFirst()

    companion object {
        private val CARDS: List<Card> =
            Suit.entries.flatMap { suit ->
                CardNumber.entries.map { cardNumber ->
                    Card(cardNumber, suit)
                }
            }
    }
}
