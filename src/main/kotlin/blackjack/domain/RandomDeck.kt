package blackjack.domain

data class RandomDeck(
    val cards: List<Card> = listOf()
) : Deck {
    override fun init(): List<Card> {
        return listOf(getNewCard(), getNewCard())
    }

    override fun hit(): Card {
        return getNewCard()
    }

    private fun getNewCard() = cards.shuffled()
        .first()

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
