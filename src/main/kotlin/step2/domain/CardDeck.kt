package step2.domain

object CardDeck {

    private val cards: Cards = CardDeckGenerator.generateCardDeck()

    fun draw(count: Int): Set<Card> {
        return cards.cards
            .shuffled()
            .take(count)
            .toSet()
    }
}
