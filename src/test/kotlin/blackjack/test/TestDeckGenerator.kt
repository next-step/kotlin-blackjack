package blackjack.test

import blackjack.domain.*

object TestDeckGenerator {

    fun generate(): Deck = deck(FakeShuffleStrategy) {
        for (suit in Symbol.values()) {
            for (value in Rank.values()) {
                +(suit of value)
            }
        }
    }
}
