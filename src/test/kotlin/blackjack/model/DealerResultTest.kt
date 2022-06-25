package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerResultTest {
    @Test
    fun `player와 win, lose를 프로퍼티로 갖는다`() {
        val player = Dealer()
        val playerResult = DealerResult(player, 100, 0)
        Assertions.assertThat(playerResult.player).isEqualTo(player)
        Assertions.assertThat(playerResult.win).isEqualTo(100)
        Assertions.assertThat(playerResult.lose).isEqualTo(0)
    }
}
