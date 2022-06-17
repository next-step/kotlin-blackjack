package blackjack.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerDecisionTest {
    @Test
    fun `입력값이 y 일 경우 hit 라고 판단한다`() {
        assertThat(PlayerDecision.ofText("y")).isEqualTo(PlayerDecision.HIT)
    }

    @Test
    fun `입력값이 n 일 경우 stand 라고 판단한다`() {
        assertThat(PlayerDecision.ofText("n")).isEqualTo(PlayerDecision.STAND)
    }

    @Test
    fun `입력값이 y 이나 n 이 아닐 경우 null 을 반환한다`() {
        assertThat(PlayerDecision.ofText("invalid!")).isNull()
    }
}
