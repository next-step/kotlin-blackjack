package domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `점수는 0보다 커야한다 0과 같거나 작다면 생성 불가`(value: Int) {
        assertThrows<IllegalArgumentException> { Score(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `점수는 0보다 커야한다 0보다 크다면 생성이 가능하다`(value: Int) {
        assertDoesNotThrow { Score(value) }
    }
}
