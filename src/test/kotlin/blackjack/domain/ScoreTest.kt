package blackjack.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ScoreTest {
    @DisplayName("1 이상 30 이하인 경우 점수 객체 생성")
    @ParameterizedTest
    @ValueSource(ints = [1, 30])
    fun constructor(input: Int) {
        assertDoesNotThrow { Score(input) }
    }

    @DisplayName("점수의 범위를 벗어난 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = [0, 31])
    fun init(input: Int) {
        assertThrows<IllegalArgumentException> { Score(input) }
    }
}