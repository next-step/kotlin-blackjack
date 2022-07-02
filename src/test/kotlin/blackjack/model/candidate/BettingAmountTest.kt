package blackjack.model.candidate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("베팅 금액 테스트")
class BettingAmountTest {

    @Test
    fun `베팅 금액이 1 미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { BettingAmount(0) }
        assertThat(exception.message).isEqualTo("베팅 금액은 최소 1 이상이어야 합니다.")
    }
}
