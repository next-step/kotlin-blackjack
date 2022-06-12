package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `game에 입장하면 player가 반환된다`() {
        val players = Game(Dealer()).enter("서정국")
        assertThat(players[0].name).isEqualTo("서정국")
    }
}
