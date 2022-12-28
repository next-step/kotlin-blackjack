package blackjack.domain.card

import blackjack.SpadeAce
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @Test
    fun `카드 - 생성 테스트`() {
        // given
        val actual = SpadeAce

        // when, then
        assertThat(actual).isEqualTo(SpadeAce)
    }

    @Test
    fun `카드 - 문자열 반환 테스트`() {
        // given
        val actual = SpadeAce

        // when, then
        assertThat(actual.toString()).isEqualTo("A스페이드")
    }

    @Test
    fun `카드 - 점수 반환 테스트`() {
        // given
        val actual = SpadeAce

        // when, then
        assertThat(actual.score()).isEqualTo(1)
    }

    @Test
    fun `카드 - 에이스 확인 테스트`() {
        // given
        val actual = SpadeAce

        // when, then
        assertThat(actual.isAce()).isTrue
    }
}
