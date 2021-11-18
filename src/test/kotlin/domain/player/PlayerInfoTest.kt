package domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerInfoTest {
    @DisplayName("플레이어 이름과 베팅한 값을 알 수 있어야 한다.")
    @Test
    fun info() {
        val name = "고정완"
        val money = 1000
        val info = PlayerInfo(PlayerName(name), BetAmount(money))
        assertAll(
            { assertThat(info.name()).isEqualTo(name) },
            { assertThat(info.bet()).isEqualTo(money) }
        )
    }
}
