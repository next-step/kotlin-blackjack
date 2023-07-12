package step2.domain

object Deck {

    private val cards: Cards = DeckGenerator.generateCardDeck()

    fun getCards(count: Int): Set<Card> {
        val drawCards = cards.cards
            .shuffled()
            .take(count)
            .toSet()

        cards.cards.removeAll { drawCards.contains(it) }

        return drawCards
    }
}
