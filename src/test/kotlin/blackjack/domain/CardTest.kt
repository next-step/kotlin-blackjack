package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardTest {

    @Test
    internal fun 에이스() {
        val blackjack = deck {
            ace()
        }.build()
        assertThat((1..4).map { blackjack.next() })
            .containsAnyOf(
                Card("A", Symbol.HEARTS),
                Card("A", Symbol.CLUBS),
                Card("A", Symbol.SPADES),
                Card("A", Symbol.DIAMONDS)
            )
    }

    @Test
    internal fun 노멀() {
        val blackjack = deck {
            normal(2..10)
        }.build()
        assertThat((1..36).map { blackjack.next() })
            .containsAnyOf(
                Card("2", Symbol.HEARTS),
                Card("3", Symbol.CLUBS),
                Card("9", Symbol.SPADES),
                Card("10", Symbol.DIAMONDS)
            )
    }

    @Test
    internal fun `잭, 퀸, 킹`() {
        val blackjack = deck {
            jack()
            queen()
            king()
        }.build()
        assertThat((1..12).map { blackjack.next() })
            .containsAnyOf(
                Card("J", Symbol.HEARTS),
                Card("Q", Symbol.CLUBS),
                Card("K", Symbol.SPADES),
                Card("J", Symbol.DIAMONDS)
            )
    }

    @Test
    internal fun `비정상 카드이름`() {
        assertThrows<IllegalArgumentException> { Card("1", Symbol.HEARTS) }
    }
}
