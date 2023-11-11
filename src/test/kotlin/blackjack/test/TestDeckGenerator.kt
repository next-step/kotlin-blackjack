package blackjack.test

import blackjack.domain.*

object TestDeckGenerator {

    fun generate(): Deck = deck(FakeShuffleStrategy) {
        for (suit in Suit.values()) {
            for (value in Value.values()) {
                +(suit with value)
            }
        }
    }
}
