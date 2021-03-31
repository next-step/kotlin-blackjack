package blackjack.model.trump

class TrumpDeck internal constructor(
    private val deck: MutableList<Card> = buildDeck()
) : Deck, MutableList<Card> by deck {
    override fun peekCard(cardNumber: CardNumber, suit: Suit): Card {
        val foundCard = deck.find { it == Card(cardNumber, suit) }

        requireNotNull(foundCard)

        deck.removeIf { it == Card(cardNumber, suit) }

        return foundCard
    }

    override fun draw(): Card {
        return deck.removeAt(0)
    }

    companion object {
        private fun buildDeck() =
            Suit.values().flatMap { suit -> CardNumber.values().map { cardNumber -> Card(cardNumber, suit) } }
                .shuffled()
                .toMutableList()
    }
}
