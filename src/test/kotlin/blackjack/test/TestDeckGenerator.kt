package blackjack.test

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.FakeShuffleStrategy
import blackjack.domain.deck

object TestDeckGenerator {

    fun generate(vararg cards: Card): Deck = deck(FakeShuffleStrategy) {
        val repeatedCards = generateSequence { cards.iterator() }.flatMap { it.asSequence() }.take(52)
        repeatedCards.forEach { +it }
    }
}
