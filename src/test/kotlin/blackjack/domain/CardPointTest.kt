package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class CardPointTest {
    @ParameterizedTest
    @ValueSource(strings = ["2", "3", "4", "6", "7", "8", "9", "10"])
    fun `getValue() 메소드 호출 시 text인자로 "2"~"10" 문자열을 입력하면 2~10 의 값을 가진 CardPoint를 리턴한다`(text: String) {
        assertThat(CardPoint.getPoint(text)).isEqualTo(CardPoint(text.toInt()))
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["0", "12"])
    fun `getValue() 메소드 호출 시 text인자로 "0", "12", 빈문자열을 입력하면 IlleagalArgumentException이 발생한다`(text: String) {
        assertThrows<IllegalArgumentException> { CardPoint.getPoint(text) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["J", "Q", "K"])
    fun `getValue() 메소드 호출 시 text인자로 "J", "Q", "K"를 입력하면 10의 값을 가진 CardPoint를 리턴한다`(text: String) {
        assertThat(CardPoint.getPoint(text)).isEqualTo(CardPoint(10))
    }

    @Test
    fun `getValue() 메소드 호출 시 text인자로 "A", chooseExtraValue로 false로 입력하면 1의 값을 가진 CardPoint를 리턴한다 `() {
        assertThat(CardPoint.getPoint("A")).isEqualTo(CardPoint(1))
    }
}
