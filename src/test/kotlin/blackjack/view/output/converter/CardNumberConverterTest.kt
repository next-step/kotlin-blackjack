package blackjack.view.output.converter

import blackjack.domain.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardNumberConverterTest {
    @ParameterizedTest
    @EnumSource(CardNumber::class)
    fun `CardNumberConverter는 CardNumber를 출력을 위한 문자열로 변환해 반환한다`(input: CardNumber) {
        val expected = when (input) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }

        assertThat(CardNumberConverter.convert(input)).isEqualTo(expected)
    }
}
