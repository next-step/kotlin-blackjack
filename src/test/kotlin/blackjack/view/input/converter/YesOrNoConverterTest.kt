package blackjack.view.input.converter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class YesOrNoConverterTest {
    @ParameterizedTest
    @ValueSource(strings = ["y", "n"])
    fun `YesOrNoConverter는 string input을 받아서 Boolean으로 변환해 반환한다`(input: String) {
        val expected = input == "y"

        assertThat(YesOrNoConverter.convert(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "test", "input"])
    fun `y 또는 n 이외의 값이 들어오면 IllegalArgumentException이 발생한다`(input: String) {
        assertThrows<IllegalArgumentException> {
            YesOrNoConverter.convert(input)
        }
    }
}
