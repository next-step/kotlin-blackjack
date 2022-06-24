package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("카드 점수 테스트")
class CardScoreTest {

    @Test
    fun `카드 점수 합 계산 기능이 정상 동작`() {
        // given
        val cardScore = CardScore(1, 2)
        val other = CardScore(3, 4)

        // when
        val sumOfScore = cardScore + other

        // then
        assertAll(
            "sum of score test",
            { assertThat(sumOfScore.score1).isEqualTo(4) },
            { assertThat(sumOfScore.score2).isEqualTo(6) }
        )
    }

    @Test
    fun `카드 점수가 0점 미만이면 예외 발생`() {
        // given, when, then
        assertAll(
            "validate card min score",
            {
                val exception = assertThrows<IllegalArgumentException> { CardScore(0, -1) }
                assertThat(exception.message).isEqualTo("카드 점수는 최소 0점 이상이어야 합니다.")
            },
            {
                val exception = assertThrows<IllegalArgumentException> { CardScore(-1, 0) }
                assertThat(exception.message).isEqualTo("카드 점수는 최소 0점 이상이어야 합니다.")
            }
        )
    }

    @ParameterizedTest
    @CsvSource(value = ["20,21,21,21", "21,22,21,21", "20,22,21,20"], delimiter = ',')
    fun `특정 숫자보다 같거나 작으면서 가장 가까운 점수를 찾아내는 기능이 정상 동작`(score1: Int, score2: Int, boundaryScore: Int, result: Int) {
        // when, then
        assertThat(CardScore(score1, score2).findNearestScoreEqualOrLessThan(boundaryScore)).isEqualTo(result)
    }

    @Test
    fun `특정 숫자보다 같거나 작으면서 가장 가까운 점수가 없으면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { CardScore(22, 22).findNearestScoreEqualOrLessThan(21) }
        assertThat(exception.message).isEqualTo("21점 보다 작거나 같으면서 가장 가까운 점수를 찾을 수 없습니다.")
    }
}
