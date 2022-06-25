package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerInfoTest {
    @Test
    fun `PlayerInfo는 블랙잭 게임에 참가하는 플레이어의 기본 정보를 보관한다`() {
        val playerInfo = PlayerInfo(
            name = PlayerName("이름"),
            betAmount = BetAmount(10_000)
        )

        assertAll(
            { assertThat(playerInfo.name).isEqualTo(PlayerName("이름")) },
            { assertThat(playerInfo.betAmount).isEqualTo(BetAmount(10_000)) }
        )
    }
}
