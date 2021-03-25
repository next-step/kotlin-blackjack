package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTests {
    @ParameterizedTest
    @ValueSource(strings = ["JACK", "QUEEN", "KING"])
    fun `King, Queen, Jack 은 10을 의미한다`(cardNumber: CardNumber) {
        assertThat(cardNumber.value).contains(10)
    }

    @ParameterizedTest
    @CsvSource("NUMBER_2, 2", "NUMBER_3, 3", "NUMBER_4, 4", "NUMBER_5, 5", "NUMBER_6, 6", "NUMBER_7, 7", "NUMBER_8, 8", "NUMBER_9, 9", "NUMBER_10, 10")
    fun `숫자를 가진 카드넘버는 그 숫자와 일치해야 한다`(cardNumber: CardNumber, exceptedValue: Int) {
        assertThat(cardNumber.value).contains(exceptedValue)
    }

    @Test
    fun `Ace는 1 또는 11이다`() {
        assertThat(CardNumber.ACE.value).containsExactlyInAnyOrder(1, 11)
    }
}
