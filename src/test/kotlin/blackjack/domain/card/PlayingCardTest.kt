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

    @Test
    fun `카드 - 문자열 반환 테스트`() {
        // given
        val actual = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // when
        val expected = "A클럽"

        // then
        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    fun `카드 - 점수 반환 테스트`() {
        // given
        val actual = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // when
        val expected = 1

        // then
        assertThat(actual.score()).isEqualTo(expected)
    }

    @Test
    fun `카드 - 에이스 확인 테스트`() {
        // given
        val actual = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // when
        val expected = true

        // then
        assertThat(actual.isAce()).isEqualTo(expected)
    }
}
