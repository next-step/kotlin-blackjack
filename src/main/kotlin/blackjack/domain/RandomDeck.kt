package blackjack.domain

data class RandomDeck(
    val cards: List<Card> = listOf()
) : Deck {
    override fun hit(): Card {
        return cards.shuffled()
            .first()
    }

    companion object {
        fun from(): Deck {
            val cards = CardSuit.values()
                .flatMap { cardSuit ->
                    CardNumber.values()
                        .map { Card(cardSuit, it) }
                        .toList()
                }
                .toList()
            return RandomDeck(cards)
        }
    }
}
