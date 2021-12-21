package blackjack

import blackjack.domain.Money
import blackjack.domain.Money.Companion.MONEY_ILLEGAL_ARGUMENTS_EXCEPTION_MESSAGE
import blackjack.domain.Money.Companion.MONEY_NUMBER_FORMAT_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @CsvSource(value = ["0, 0", "1, 1", "2, 2", "3, 3", "4, 4"])
    fun `String을 입력받아 Money를 생성할 수 있다`(stringValue: String, intValue: Int) {
        val stringMoney = Money.from(stringValue)

        assertThat(stringMoney).isEqualTo(Money(intValue))
    }

    @ParameterizedTest
    @ValueSource(strings = ["!#!@#", "abc", "2dsoa", "aosdk"])
    fun `Money에 Int가 아닌 값을 입력하면 NumberFormatException을 던진다`(stringValue: String) {
        Assertions
            .assertThatExceptionOfType(NumberFormatException::class.java)
            .isThrownBy {
                Money.from(stringValue)
            }
            .withMessage(MONEY_NUMBER_FORMAT_EXCEPTION_MESSAGE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "-2", "-3", "-4", "-5"])
    fun `Money에 음수를 입력하면 IllegalArgumentsException을 던진다`(stringValue: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Money.from(stringValue)
            }
            .withMessage(MONEY_ILLEGAL_ARGUMENTS_EXCEPTION_MESSAGE.format(stringValue))
    }
}
