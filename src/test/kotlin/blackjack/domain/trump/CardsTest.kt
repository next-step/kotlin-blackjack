package blackjack.domain.trump

import blackjack.fixture.CardFixture.Companion.CLOB_FOUR
import blackjack.fixture.CardFixture.Companion.DIAMOND_KING
import blackjack.fixture.CardFixture.Companion.HEART_ACE
import blackjack.fixture.CardFixture.Companion.SPADE_THREE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class CardsTest {


    @Test
    fun `ACE를 포함한 카드덱의 점수 총합이 11점 이하면 ACE를 11점으로 계산한다`() {
        // given
        val cards = Cards(listOf(HEART_ACE, SPADE_THREE, CLOB_FOUR))
        val expected = 18

        // when
        val score = cards.score()

        // then
        assertThat(score).isEqualTo(expected)
    }

    @Test
    fun `ACE를 포함한 카드덱의 점수 총합이 11점 초과면 ACE를 1점으로 계산한다`() {
        // given
        val cards = Cards(listOf(HEART_ACE, SPADE_THREE, CLOB_FOUR, DIAMOND_KING))
        val expected = 18

        // when
        val score = cards.score()

        // then
        assertThat(score).isEqualTo(expected)
    }

    @Test
    fun `카드를 추가할 수 있다`() {
        // given
        val before = Cards(listOf(HEART_ACE, SPADE_THREE, CLOB_FOUR))

        // when
        val after = before.add(DIAMOND_KING)

        // then
        assertAll(
            { assertThat(before.values.size).isLessThan(after.values.size) },
            { assertThat (after.values).contains(DIAMOND_KING, HEART_ACE, SPADE_THREE, CLOB_FOUR)},
            { assertThat (before.values).isNotIn(DIAMOND_KING)}
        )
    }
}
