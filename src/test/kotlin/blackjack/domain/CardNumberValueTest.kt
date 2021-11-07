package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class CardNumberValueTest {
    @ParameterizedTest
    @ValueSource(strings = ["2", "3", "4", "6", "7", "8", "9", "10"])
    fun `getValue() 메소드 호출 시 text인자로 "2"~"10" 문자열을 입력하면 2~10 의 값을 가진 CardNumberValue를 리턴한다`(text: String) {
        assertThat(CardNumberValue.getValue(text)).isEqualTo(CardNumberValue(text.toInt()))
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["0", "11"])
    fun `getValue() 메소드 호출 시 text인자로 "0", "11", 빈문자열을 입력하면 IlleagalArgumentException이 발생한다`(text: String) {
        assertThrows<IllegalArgumentException> { CardNumberValue.getValue(text) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["J", "Q", "K"])
    fun `getValue() 메소드 호출 시 text인자로 "J", "Q", "K"를 입력하면 10의 값을 가진 CardNumberValue를 리턴한다`(text: String) {
        assertThat(CardNumberValue.getValue(text)).isEqualTo(CardNumberValue(10))
    }

    @Test
    fun `getValue() 메소드 호출 시 text인자로 "A", chooseExtraValue로 false로 입력하면 1의 값을 가진 CardNumberValue를 리턴한다 `() {
        assertThat(CardNumberValue.getValue("A")).isEqualTo(CardNumberValue(1))
    }

    @Test
    fun `getValue() 메소드 호출 시 text인자로 "A", chooseExtraValue로 true로 입력하면 11의 값을 가진 CardNumberValue를 리턴한다 `() {
        assertThat(CardNumberValue.getValue("A", true)).isEqualTo(CardNumberValue(11))
    }
}
