package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @Test
    fun `카드 - 생성 테스트`() {
        // given
        val actual = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // when
        val expected = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
