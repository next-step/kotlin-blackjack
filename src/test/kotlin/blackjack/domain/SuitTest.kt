package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SuitTest {

    @Test
    fun `shape name`() {
        assertThat(Suit.HEART.shapeName).isEqualTo("하트")
        assertThat(Suit.CLUB.shapeName).isEqualTo("클로버")
        assertThat(Suit.SPADE.shapeName).isEqualTo("스페이드")
        assertThat(Suit.DIAMOND.shapeName).isEqualTo("다이아몬드")
    }
}
