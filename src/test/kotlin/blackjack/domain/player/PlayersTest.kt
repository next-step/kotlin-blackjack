package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `Players 는 딜러와 여러명의 플레이어로 구성되어 있다`() {
        val dealer = Dealer()
        val players = listOf(Player("a"), Player("b"))
        val allPlayers = Players(dealer, players)

        assertThat(allPlayers.dealer).isEqualTo(dealer)
        assertThat(allPlayers.players).isEqualTo(players)
    }
}
