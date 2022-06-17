package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `game에 입장하면 dealer도 player로 참가한다`() {
        val dealer = Dealer()
        val players = Game(dealer).enter("서정국")
        assertThat(players.find { player -> player.name == "딜러" }).isEqualTo(dealer)
    }

    @Test
    fun `dealer는 첫 번째 player다`() {
        val players = Game(Dealer()).enter("서정국")
        assertThat(players[0].name).isEqualTo("딜러")
    }

    @Test
    fun `game에 입장하면 player가 반환된다`() {
        val players = Game(Dealer()).enter("서정국")
        assertThat(players[1].name).isEqualTo("서정국")
    }
}
