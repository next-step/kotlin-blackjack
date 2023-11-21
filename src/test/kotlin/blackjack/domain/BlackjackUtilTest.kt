package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BlackjackUtilTest {

    @Test
    fun `카드 목록을 받아 점수 합계를 반환한다`() {
        val cards = listOf(
            Card(CardSuitInfo.SPADE, CardNumberInfo.TEN),
            Card(CardSuitInfo.HEART, CardNumberInfo.TEN),
            Card(CardSuitInfo.DIAMOND, CardNumberInfo.TEN),
        )

        val (minScore, maxScore) = BlackjackUtil.computeScore(cards)

        assertThat(minScore).isEqualTo(30)
        assertThat(maxScore).isEqualTo(30)
    }

    @Test
    fun `ACE가 포함된 카드 목록에서 도출 가능한 최소, 최대점수를 반환한다`() {
        val cards = listOf(
            Card(CardSuitInfo.SPADE, CardNumberInfo.ACE),
            Card(CardSuitInfo.HEART, CardNumberInfo.ACE),
            Card(CardSuitInfo.DIAMOND, CardNumberInfo.ACE),
        )

        val (minScore, maxScore) = BlackjackUtil.computeScore(cards)

        assertThat(minScore).isEqualTo(3)
        assertThat(maxScore).isEqualTo(13)
    }

    @ParameterizedTest
    @CsvSource(value = ["20,false", "21,false", "22,true"])
    fun `점수가 21점을 초과하는지 확인한다`(score: Int, expected: Boolean) {
        assertThat(BlackjackUtil.isBust(score)).isEqualTo(expected)
    }
}
