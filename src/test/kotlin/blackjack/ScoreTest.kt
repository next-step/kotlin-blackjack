package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ScoreTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 100])
    fun `점수를 의미하는 객체로 Value class 이다`(i: Int) {
        assertThat(Score(i).value).isSameAs(i)
        assertThat(Score(i)).isEqualTo(Score(i))
    }

    @Test
    fun `21점을 넘었는지 판단하는 판단한다`() {
        assertThat(Score(1).isLose()).isFalse()
        assertThat(Score(21).isLose()).isFalse()
        assertThat(Score(22).isLose()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `0보다 작은 수는 들어갈 수 없다`(i: Int) {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            Score(i)
        }
    }
}
