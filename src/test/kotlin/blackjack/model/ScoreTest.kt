package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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
        assertThat(Score(1).isBust()).isFalse()
        assertThat(Score(21).isBust()).isFalse()
        assertThat(Score(22).isBust()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `0보다 작은 수는 들어갈 수 없다`(i: Int) {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            Score(i)
        }
    }

    @ParameterizedTest
    @CsvSource("26,25,false", "26,20,false,", "21,20,true", "21,1,true", "20,21,false")
    fun `두 점수를 비교해 승자를 판단한다`(a: Int, b: Int, c: Boolean) {
        assertThat(Score(a).isWinThan(Score(b))).isEqualTo(c)
    }
}
