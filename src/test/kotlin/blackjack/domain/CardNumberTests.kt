package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTests {
    @ParameterizedTest
    @ValueSource(strings = ["JACK", "QUEEN", "KING"])
    fun `King, Queen, Jack 은 10을 의미한다`(cardNumber: CardNumber) {
        assertThat(cardNumber.number).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource("ACE, 1", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT, 8", "NINE, 9", "TEN, 10")
    fun `숫자를 가진 카드넘버는 그 숫자와 일치해야 한다`(cardNumber: CardNumber, exceptedValue: Int) {
        assertThat(cardNumber.number).isEqualTo(exceptedValue)
    }
}
