package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerResultTest {
    @Test
    fun `player와 win을 프로퍼티로 갖는다`() {
        val player = Player("player")
        val playerResult = PlayerResult(player, true)
        Assertions.assertThat(playerResult.player).isEqualTo(player)
        Assertions.assertThat(playerResult.win).isEqualTo(true)
    }
}
