package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("카드 점수 테스트")
class CardScoreTest {

    @Test
    fun `카드 점수 합 계산 기능이 정상 동작`() {
        // given
        val cardScore = CardScore(1, 2)
        val other = CardScore(3, 4)

        // when
        val sumOfScore = cardScore.plus(other)

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
}
