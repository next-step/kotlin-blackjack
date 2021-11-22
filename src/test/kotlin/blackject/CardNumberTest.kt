package blackject

import blackject.model.card.CardNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardNumberTest {

    @EnumSource(CardNumber::class)
    @ParameterizedTest
    @DisplayName("카드 숫자 별 값 비교하기")
    fun `check card number`(card: CardNumber) {
        val cardNumber = CardNumber.getNumberMinValue(card)

        Assertions.assertThat(cardNumber).isGreaterThanOrEqualTo(1)
        Assertions.assertThat(cardNumber).isLessThanOrEqualTo(11)
    }

    @Test
    @DisplayName("킹 카드의 숫자 확인")
    fun `check king card number`() {
        val king = CardNumber.KING
        val expectedMinNumber = 10

        val number = CardNumber.getNumberMinValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("잭 카드의 숫자 확인")
    fun `check jack card number`() {
        val king = CardNumber.JACK
        val expectedMinNumber = 10

        val number = CardNumber.getNumberMinValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("퀸 카드의 숫자 확인")
    fun `check queen card number`() {
        val king = CardNumber.QUEEN
        val expectedMinNumber = 10

        val number = CardNumber.getNumberMinValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @EnumSource(names = ["QUEEN", "JACK", "KING"])
    @ParameterizedTest
    @DisplayName("local 종류의 카드 확인")
    fun `check loyal card`(cardNumber: CardNumber) {
        val number = CardNumber.isRoyal(cardNumber.numberName)

        Assertions.assertThat(number).isEqualTo(true)
    }

    @EnumSource(names = ["ACE"])
    @ParameterizedTest
    @DisplayName("ace 종류의 카드 확인")
    fun `check ace card`(cardNumber: CardNumber) {
        val number = CardNumber.isAce(cardNumber.numberName)

        Assertions.assertThat(number).isEqualTo(true)
    }
}
