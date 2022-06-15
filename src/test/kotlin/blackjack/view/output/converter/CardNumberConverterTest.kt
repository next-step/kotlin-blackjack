package blackjack.view.output.converter

import blackjack.domain.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardNumberConverterTest {
    @ParameterizedTest
    @CsvSource(
        "ACE,A",
        "TWO,2",
        "THREE,3",
        "FOUR,4",
        "FIVE,5",
        "SIX,6",
        "SEVEN,7",
        "EIGHT,8",
        "NINE,9",
        "TEN,10",
        "JACK,J",
        "QUEEN,Q",
        "KING,K"
    )
    fun `CardNumberConverter는 CardNumber를 출력을 위한 문자열로 변환해 반환한다`(cardNumber: CardNumber, expected: String) {
        assertThat(CardNumberConverter.convert(cardNumber)).isEqualTo(expected)
    }
}
