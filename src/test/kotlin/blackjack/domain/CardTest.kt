package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `카드 생성`() {
        // given
        val denomination = Denomination.TWO
        val suit = Suit.SPADE

        // when
        val actual = Card.of(denomination, suit).denomination

        // then
        val expected = Denomination.TWO
        assertThat(actual).isEqualTo(expected)
    }
}
