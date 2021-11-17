package domain.player

import exception.IllegalBetException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BetAmountTest {

    @DisplayName("베팅 금액은 음수가 될 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = [-1, -11, -111, -1111])
    fun constructor(money: Int) {
        assertThatExceptionOfType(IllegalBetException::class.java)
            .isThrownBy { BetAmount(money) }
    }

    @DisplayName("곱하기 연산자가 재정의 되어 있어야 한다.")
    @Test
    fun times() {
        assertThat(BetAmount(1000) * 1.5)
            .isEqualTo(1500)
    }
}
