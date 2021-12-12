package blackjack

import blackjack.domain.Score
import blackjack.domain.Score.Companion.SCORE_MUST_BE_ZERO_OR_MORE_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.lang.IllegalArgumentException

class ScoreTest {

    @ParameterizedTest(name = "Score는 더할 수 있다")
    @CsvSource(value = ["1, 2, 3", "2, 2, 4", "100, 10, 110"])
    fun `Score는 더할 수 있다`(score1: Int, score2: Int, sum: Int) {
        val firstScore = Score(score1)
        val secondScore = Score(score2)
        assertThat(firstScore + secondScore).isEqualTo(Score(sum))
    }

    @ParameterizedTest(name = "Score는 비교할 수 있다")
    @CsvSource(value = ["1, 2, false", "3, 2, true", "100, 100, false"])
    fun `Score는 비교할 수 있다`(score1: Int, score2: Int, expected: Boolean) {
        val firstScore = Score(score1)
        val secondScore = Score(score2)
        assertThat(firstScore > secondScore).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Score는 Bust를 알 수 있다")
    @CsvSource(value = ["22, true", "4, false", "8, false", "32, true"])
    fun `Score는 Bust를 알 수 있다`(score: Int, expected: Boolean) {
        val score = Score(score)
        assertThat(score.isBust).isEqualTo(expected)
    }

    @Test
    fun `Score는 Blackjack을 알 수 있다`() {
        val score = Score(21)
        assertThat(score.isBlackjack).isTrue
    }

    @Test
    fun `Score는 0미만일 수 없다`() {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Score(-2)
            }
            .withMessage(SCORE_MUST_BE_ZERO_OR_MORE_EXCEPTION_MESSAGE)
    }

    @Test
    fun `Score는 11이상이면 Ace 스코어로 1을 반환한다`() {
        val score = Score(11)

        val aceScore = score.getAceScore()

        assertThat(aceScore).isEqualTo(Score(1))
    }

    @Test
    fun `Score는 11미만이면 Ace 스코어로 11을 반환한다`() {
        val score = Score(10)

        val aceScore = score.getAceScore()

        assertThat(aceScore).isEqualTo(Score(11))
    }
}
