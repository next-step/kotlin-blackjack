package blackjack.playingcard

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class ValueTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, -11])
    internal fun `값은 음수일 수 없다`(integer: Int) {
        assertThatIllegalArgumentException().isThrownBy { Value(integer) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 11])
    internal fun `값은 정수를 가진다`(integer: Int) {
        assertThat(Value(integer).toInt()).isEqualTo(integer)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "1, 1",
        "11, 2"
    )
    internal fun `값은 더할 수 있다`(a: Int, b: Int) {
        val valueA = Value(a)
        val valueB = Value(b)
        val expected: Int = valueA.toInt() + valueB.toInt()
        val actual: Int = (valueA + valueB).toInt()

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "1, 1",
        "11, 2"
    )
    internal fun `값은 뺄 수 있다`(a: Int, b: Int) {
        val valueA = Value(a)
        val valueB = Value(b)
        val expected: Int = valueA.toInt() - valueB.toInt()
        val actual: Int = (valueA - valueB).toInt()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `값은 객체가 달라도, 가지고 있는 정수가 같다면 동일하다`() {
        val one = Value(3)
        val another = Value(3)

        assertThat(one).isEqualTo(another)
    }

    @Test
    internal fun `값은 대소 비교를 할 수 있다`() {
        assertAll(
            { assertThat(Value(10)).isGreaterThan(Value(5)) },
            { assertThat(Value(10)).isLessThan(Value(15)) }
        )
    }
}
