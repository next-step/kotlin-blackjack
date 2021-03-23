package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `점수는 0보다 커야한다 0과 같거나 작다면 생성 불가`(value: Int) {
        assertThrows<IllegalArgumentException> { Score.of(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `점수는 0보다 커야한다 0보다 크다면 생성이 가능하다`(value: Int) {
        assertDoesNotThrow { Score.of(value) }
    }

    @Test
    fun `score는 vo이기 때문에 값이 같다면 같은 객체로 취급한다`() {
        assertThat(Score.of(1)).isEqualTo(Score.of(1))
    }

    @Test
    fun `plus 추가 1 + 1 = 2`() {
        val one = Score.of(1)
        assertThat(one + one).isEqualTo(Score.of(2))
    }

    @Test
    fun `score 끼리는 비교 연산이 가능하다`() {
        val one = Score.of(1)
        val two = Score.of(2)
        assertThat(one == one).isTrue()
        assertThat(one >= one).isTrue()
        assertThat(two >= one).isTrue()
        assertThat(two < one).isFalse()
    }
}
