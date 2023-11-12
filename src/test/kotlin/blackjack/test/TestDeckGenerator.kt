package blackjack.test

import blackjack.domain.*

object TestDeckGenerator {

    fun generate(vararg cards: Card): Deck = deck(FakeShuffleStrategy) {
        val repeatedCards = generateSequence { cards.iterator() }.flatMap { it.asSequence() }.take(52)
        repeatedCards.forEach { +it }
    }

    private fun generateFullDeck(): List<Card> {
        val fullDeck = mutableListOf<Card>()
        for (suit in Symbol.values()) {
            for (value in Rank.values()) {
                fullDeck.add(Card(suit, value))
            }
        }
        return fullDeck
    }
}
