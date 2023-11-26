package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `다이아 카드 생성`() {
        val sut = Card.diamond(Number.SIX)

        assertThat(sut.number).isEqualTo(Number.SIX)
        assertThat(sut.shape).isEqualTo(Shape.DIAMOND)
    }

    @Test
    fun `클로버 카드 생성`() {
        val sut = Card.clover(Number.TEN)

        assertThat(sut.number).isEqualTo(Number.TEN)
        assertThat(sut.shape).isEqualTo(Shape.CLOVER)
    }

    @Test
    fun `하트 카드 생성`() {
        val sut = Card.heart(Number.NINE)

        assertThat(sut.number).isEqualTo(Number.NINE)
        assertThat(sut.shape).isEqualTo(Shape.HEART)
    }

    @Test
    fun `스페이드 카드 생성`() {
        val sut = Card.spade(Number.TWO)

        assertThat(sut.number).isEqualTo(Number.TWO)
        assertThat(sut.shape).isEqualTo(Shape.SPADE)
    }
}
