package blackjack

import blackjack.domain.Player
import blackjack.domain.Players
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var firstPlayer: Player
    private lateinit var secondPlayer: Player
    private lateinit var players: Players

    @BeforeEach
    fun `set up`() {
        firstPlayer = Player("jacob")
        secondPlayer = Player("joseph")
        players = Players(listOf(firstPlayer, secondPlayer))
    }

    @Test
    fun `size check`() {
        assertTrue(players.size() == 2)
    }

    @Test
    fun `find player`() {
        val jacob = players.findPlayer(0)
        val joseph = players.findPlayer(1)

        assertTrue(jacob == firstPlayer)
        assertTrue(joseph == secondPlayer)
    }
}
