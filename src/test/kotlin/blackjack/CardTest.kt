package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    internal fun 에이스() {
        val blackjack = blackjack {
            ace()
        }.build()
        assertThat(blackjack).contains(
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
        assertThat(blackjack).contains(
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
        assertThat(blackjack).contains(
            Card("J", Symbol.HEARTS),
            Card("Q", Symbol.CLUBS),
            Card("K", Symbol.SPADES),
            Card("J", Symbol.DIAMONDS)
        )
    }
}
