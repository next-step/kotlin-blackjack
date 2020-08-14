package blackjack.domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var firstPlayer: Player
    private lateinit var secondPlayer: Player
    private lateinit var players: Players

    @BeforeEach
    fun `set up`() {
        firstPlayer = Player("mark")
        secondPlayer = Player("harry")
        players = Players(listOf(firstPlayer, secondPlayer))
    }

    @Test
    fun `find player`() {
        // when
        val mark = players.findPlayer(0)
        val harry = players.findPlayer(1)

        // then
        assertTrue(mark == firstPlayer)
        assertTrue(harry == secondPlayer)
    }

    @Test
    fun `size of players`() {
        assertTrue(players.size() == 2)
    }
}
