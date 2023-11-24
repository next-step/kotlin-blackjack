package blackjack.domain

import blackjack.domain.BlackjackUtil.winAgainstDealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BlackjackUtilTest {
    @Test
    fun `ACE가 포함된 카드 목록에서 도출 가능한 최소, 최대점수를 반환한다`() {
        val cards = listOf(
            Card(CardSuitInfo.SPADE, CardNumberInfo.ACE),
            Card(CardSuitInfo.HEART, CardNumberInfo.ACE),
            Card(CardSuitInfo.DIAMOND, CardNumberInfo.ACE),
        )

        val hand = Hand(cards)
        val (minScore, maxScore) = BlackjackUtil.computeScore(hand)

        assertThat(minScore).isEqualTo(3)
        assertThat(maxScore).isEqualTo(13)
    }

    @ParameterizedTest
    @CsvSource(value = ["20,false", "21,false", "22,true"])
    fun `점수가 21점을 초과하는지 확인한다`(score: Int, expected: Boolean) {
        assertThat(BlackjackUtil.isBust(score)).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 21, 22])
    fun `딜러가 bust인 경우 항상 승리한다`(userScore: Int) {
        val dealerScore = 22
        assertThat(winAgainstDealer(userScore, dealerScore)).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 21])
    fun `딜러가 bust가 아니면 bust된 유저는 항상 패배한다`(dealerScore: Int) {
        val userScore = 22
        assertThat(winAgainstDealer(userScore, dealerScore)).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 21])
    fun `유저, 딜러 중 bust가 없을시 동점이면 무승부 처리한다`(score: Int) {
        assertThat(winAgainstDealer(score, score)).isNull()
    }

    @ParameterizedTest
    @CsvSource(value = ["21,1,true", "1,21,false"])
    fun `유저, 딜러 중 bust가 없을시 높은점수가 승리한다`(userScore: Int, dealerScore: Int, expected: Boolean) {
        assertThat(winAgainstDealer(userScore, dealerScore)).isEqualTo(expected)
    }
}
