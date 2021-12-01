package blackjack.domain.card

import blackjack.error.ScoreOutOfBoundsException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("점수(Score)")
class ScoreTest {

    @Test
    fun `점수는 0으로 이루어질 수 있다`() {
        val score = Score.from(0)

        assertAll(
            { assertThat(score).isNotNull },
            { assertThat(score).isExactlyInstanceOf(Score::class.java) },
        )
    }

    @RepeatedTest(value = 32, name = "현재, {currentRepetition}/{totalRepetitions}")
    fun `점수는 1~32 사이의 숫자로 이뤄진다`(repetitionInfo: RepetitionInfo) {
        val score = Score.from(repetitionInfo.currentRepetition)

        assertAll(
            { assertThat(score).isNotNull },
            { assertThat(score).isExactlyInstanceOf(Score::class.java) },
        )
    }

    @ParameterizedTest(name = "입력걊: {0}")
    @ValueSource(ints = [-1, -10, -100, Integer.MIN_VALUE])
    fun `점수는 음수가 될 수 없다`(negativeNumber: Int) {
        val exception = assertThrows<ScoreOutOfBoundsException> { Score.from(negativeNumber) }

        assertThat(exception.message).isEqualTo("'%s'는 스코어의 범위를 벗어난 값 입니다.".format(negativeNumber))
    }

    @ParameterizedTest(name = "입력걊: {0}")
    @CsvSource(value = ["0:1:1", "1:1:2", "21:11:32"], delimiter = ':')
    fun `점수는 다른 점수를 더할 수 있다`(firstScoreInt: Int, secondScoreInt: Int, expectedInt: Int) {
        val expected = Score.from(expectedInt)

        val firstScore = Score.from(firstScoreInt)
        val secondScore = Score.from(secondScoreInt)
        val actual = firstScore + secondScore

        assertThat(actual).isEqualTo(expected)
    }
}
