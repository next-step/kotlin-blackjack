package blackjack.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BettingMoneyTest {
    @DisplayName("숫자가 아닌 값을 입력한 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = ["abc", "1000a", "1,000"])
    fun validateNumber(input: String) {
        assertThrows<IllegalArgumentException> { BettingMoney(input) }
    }
}