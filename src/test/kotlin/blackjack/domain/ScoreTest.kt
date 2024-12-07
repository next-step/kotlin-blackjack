package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ScoreTest {
    @ParameterizedTest
    @CsvSource(value = ["21, false", "22, true"])
    fun `버스트 상태 확인 가능`(
        scoreValue: Int,
        expected: Boolean,
    ) {
        val score = Score(scoreValue)

        val actual = score.isBust()

        assertThat(actual).isEqualTo(expected)
    }
}
