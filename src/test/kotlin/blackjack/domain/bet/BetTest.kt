package blackjack.domain.bet

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetTest {
    @Test
    fun `베팅 금액 - 0 입력에 대한 예외처리 테스트`() {
        // given, when, then
        assertThatThrownBy { Bet(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("베팅 금액은 0보다 커야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "blackjack"])
    fun `베팅 금액 - 숫자 이외의 값 입력에 대한 예외처리 테스트`(input: String) {
        // given, when, then
        assertThatThrownBy { Bet(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("숫자 이외의 값은 입력할 수 없습니다.")
    }
}
