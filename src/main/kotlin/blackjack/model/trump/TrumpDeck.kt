package blackjack.model.trump

class TrumpDeck constructor(
    private val deck: MutableList<Card> = buildDeck()
) : Deck, MutableList<Card> by deck {
    override fun draw(): Card? {
        if (deck.isEmpty()) {
            return null
        }

        return deck.removeAt(0)
    }

    companion object {
        private fun buildDeck() =
            Suit.values().flatMap { suit -> CardNumber.values().map { cardNumber -> Card(cardNumber, suit) } }
                .shuffled()
                .toMutableList()
    }
}
