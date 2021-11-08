package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = [-2, -1])
    fun `0 미만의 점수는 불가능하다`(score: Int) {
        assertThrows<IllegalArgumentException> { Score(score) }
    }

    @Test
    fun `점수가 21점이면 BlackJack이다`() {
        val score = Score(21)

        assertThat(score.isBlackJack()).isTrue
        assertThat(score.isBust()).isFalse
        assertThat(score.canHit()).isFalse
    }

    @ParameterizedTest
    @ValueSource(ints = [22, 23, 100])
    fun `점수가 21점 초과면 Bust이다`(scoreAsInt: Int) {
        val score = Score(scoreAsInt)

        assertThat(score.isBlackJack()).isFalse
        assertThat(score.isBust()).isTrue
        assertThat(score.canHit()).isFalse
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20])
    fun `점수가 21점 미만이면 Hit이 가능하다`(scoreAsInt: Int) {
        val score = Score(scoreAsInt)

        assertThat(score.isBlackJack()).isFalse
        assertThat(score.isBust()).isFalse
        assertThat(score.canHit()).isTrue
    }

    @ParameterizedTest
    @CsvSource(value = ["1,11", "5,15", "11,21", "12,12", "20,20", "21,21"])
    fun `ace 보정을 받으면 11 이하의 점수는 10이 더해진다`(scoreAsInt: Int, expected: Int) {
        val score = Score(scoreAsInt)

        val result = score.aceRevised

        assertThat(result.value).isEqualTo(expected)
    }

    @Test
    fun `두 Score를 더한다`() {
        val score1 = Score(1)
        val score2 = Score(10)

        val result = score1 + score2

        assertThat(result.value).isEqualTo(11)
    }

    @Test
    fun `Score 들의 합을 구한다`() {
        val scores = listOf(
            Score(1),
            Score(10),
            Score(5),
        )

        val result = scores.sum()

        assertThat(result.value).isEqualTo(16)
    }
}
