package blackjack

import blackjack.domain.Player
import blackjack.domain.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `문자로 출력되는지 확인`() {
        val player1 = Player("jacob")
        val player2 = Player("joseph")
        val players = Players(listOf(player1, player2))
        assertThat(players.toString()).isEqualTo("jacob")
    }
}
