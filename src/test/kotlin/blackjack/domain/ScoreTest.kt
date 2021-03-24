package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ScoreTest {
    @DisplayName("0 이상 30 이하인 경우 점수 객체 생성")
    @ParameterizedTest
    @ValueSource(ints = [0, 30])
    fun constructor(input: Int) {
        assertDoesNotThrow { Score(input) }
    }

    @DisplayName("점수의 범위를 벗어난 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = [-1, 31])
    fun init(input: Int) {
        assertThrows<IllegalArgumentException> { Score(input) }
    }

    @DisplayName("점수 비교의 결과로 ResultType 반환")
    @Test
    fun compareTo() {
        val win = Score(2).compareTo(Score(1))
        val draw = Score(1).compareTo(Score(1))
        val lose = Score(1).compareTo(Score(2))

        assertAll(
            { assertThat(win).isEqualTo(ResultType.WIN) },
            { assertThat(draw).isEqualTo(ResultType.DRAW) },
            { assertThat(lose).isEqualTo(ResultType.LOSE) },
        )
    }
}
