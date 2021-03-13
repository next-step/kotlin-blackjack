package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    // blackjack {
    //     ace(1, 10)
    //     normal(2..10)
    //     jack(10)
    //     queen(10)
    //     king(10)
    // }

    @Test
    internal fun 에이스() {
        val blackjack = blackjack {
            ace()
        }.build()
        assertThat(blackjack.deck).contains(
            Card("A", Symbol.HEARTS),
            Card("A", Symbol.CLUBS),
            Card("A", Symbol.SPADES),
            Card("A", Symbol.DIAMONDS)
        )
    }

    @Test
    internal fun 노멀() {
        val blackjack = blackjack {
            normal(2..10)
        }.build()
        assertThat(blackjack.deck).contains(
            Card("2", Symbol.HEARTS),
            Card("3", Symbol.CLUBS),
            Card("9", Symbol.SPADES),
            Card("10", Symbol.DIAMONDS)
        )
    }

    @Test
    internal fun `잭, 퀸, 킹`() {
        val blackjack = blackjack {
            jack()
            queen()
            king()
        }.build()
        assertThat(blackjack.deck).contains(
            Card("J", Symbol.HEARTS),
            Card("Q", Symbol.CLUBS),
            Card("K", Symbol.SPADES),
            Card("J", Symbol.DIAMONDS)
        )
    }
}
