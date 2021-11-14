package blackject

import blackject.model.card.CardNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardNumberTest {

    @Test
    @DisplayName("2번 카드 값 구하기")
    fun `check 2 card number`() {
        val card = CardNumber.TWO

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(2)
    }

    @Test
    @DisplayName("2번 카드 값 구하기")
    fun `check 3 card number`() {
        val card = CardNumber.THREE

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(3)
    }

    @Test
    @DisplayName("2번 카드 값 구하기")
    fun `check 4 card number`() {
        val card = CardNumber.FOUR

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(4)
    }

    @Test
    @DisplayName("2번 카드 값 구하기")
    fun `check 5 card number`() {
        val card = CardNumber.FIVE

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(5)
    }

    @Test
    @DisplayName("6번 카드 값 구하기")
    fun `check 6 card number`() {
        val card = CardNumber.SIX

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(6)
    }

    @Test
    @DisplayName("7번 카드 값 구하기")
    fun `check 7 card number`() {
        val card = CardNumber.SEVEN

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(7)
    }

    @Test
    @DisplayName("8번 카드 값 구하기")
    fun `check 8 card number`() {
        val card = CardNumber.EIGHT

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(8)
    }

    @Test
    @DisplayName("9번 카드 값 구하기")
    fun `check 9 card number`() {
        val card = CardNumber.NINE

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(9)
    }

    @Test
    @DisplayName("10번 카드 값 구하기")
    fun `check 10 card number`() {
        val card = CardNumber.TEN

        val cardNumber = CardNumber.getNumberValue(card)

        Assertions.assertThat(cardNumber).isEqualTo(10)
    }

    @Test
    @DisplayName("Ace 카드의 최소 숫자 구하기")
    fun `check ace number of min`() {
        val ace = CardNumber.ACE
        val expectedMinNumber = 1

        val number = CardNumber.getNumberValue(ace)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("Ace 카드의 최대 숫자 구하기")
    fun `check ace number of max`() {
        val ace = CardNumber.ACE
        val expectedMinNumber = 11

        val number = CardNumber.getNumberValue(ace, true)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("킹 카드의 숫자 확인")
    fun `check king card number`() {
        val king = CardNumber.KING
        val expectedMinNumber = 10

        val number = CardNumber.getNumberValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("잭 카드의 숫자 확인")
    fun `check jack card number`() {
        val king = CardNumber.JACK
        val expectedMinNumber = 10

        val number = CardNumber.getNumberValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("퀸 카드의 숫자 확인")
    fun `check queen card number`() {
        val king = CardNumber.QUEEN
        val expectedMinNumber = 10

        val number = CardNumber.getNumberValue(king)

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
