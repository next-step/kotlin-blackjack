package blackjack.view.input.converter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountConverterTest {
    @ParameterizedTest
    @ValueSource(strings = ["10000", "100", "56210"])
    fun `BetAmountConverter는 string input을 받아서 BetAmount로 변환해 반환한다`(input: String) {
        assertThat(BetAmountConverter.convert(input).value).isEqualTo(input.toInt())
    }
}
