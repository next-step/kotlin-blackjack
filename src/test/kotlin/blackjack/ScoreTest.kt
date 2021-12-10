package blackjack

import blackjack.domain.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
        val firstScore = Score(score)
        assertThat(firstScore.isBust).isEqualTo(expected)
    }
}
