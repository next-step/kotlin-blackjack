package blackjack.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerPropertyTest {
    @Test
    fun `플레이어의 이름을 가지고 있다`() {
        val name = "vivian"
        assertThat(PlayerProperty(name, 1000.0).name).isEqualTo(name)
    }

    @Test
    fun `플레이어의 베팅 금액을 가지고 있다`() {
        val bet = 1000.0
        assertThat(PlayerProperty("vivian", bet).bet).isEqualTo(bet)
    }
}
