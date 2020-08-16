package blackjack

import blackjack.domain.BetMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetMoneyTest {

    @ParameterizedTest
    @DisplayName("베팅 머니 입력값 유효성 체크")
    @ValueSource(strings = ["", "$", "Z", "ACE"])
    fun validateBetMoney(value: String) {
        val betMoney = BetMoney.newInstance(value)

        assertThat(betMoney).isNull()
    }

    @Test
    @DisplayName("베팅 머니 입력값 체크")
    fun validateBetMoney() {
        val betMoney = BetMoney.newInstance("1000")

        assertThat(betMoney)
            .isEqualTo(BetMoney.newInstance("1000"))
    }
}
