package step2.domain

object CardDeck {

    private val cards: Cards = CardDeckGenerator.generateCardDeck()

    fun draw(count: Int): Set<Card> {
        val drawCards = cards.cards
            .shuffled()
            .take(count)
            .toSet()

        cards.cards.removeAll { drawCards.contains(it) }

        return drawCards
    }
}
