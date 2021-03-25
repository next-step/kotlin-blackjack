package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTests {
    @ParameterizedTest
    @ValueSource(strings = ["JACK", "QUEEN", "KING"])
    fun `King, Queen, Jack 은 10을 의미한다`(cardNumber: CardNumber) {
        assertThat(cardNumber.value).isEqualTo(10)
    }
}
