package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var firstPlayer: Player
    private lateinit var secondPlayer: Player
    private lateinit var players: Players

    @BeforeEach
    fun setUp() {
        firstPlayer = Player("mark")
        secondPlayer = Player("harry")
        players = Players(listOf(firstPlayer, secondPlayer))
    }

    @Test
    fun `플레이어 찾기`() {
        // when
        val mark = players.findPlayer(0)
        val harry = players.findPlayer(1)

        // then
        assertThat(mark).isEqualTo(firstPlayer)
        assertThat(harry).isEqualTo(secondPlayer)
    }

    @Test
    fun `플레이어 크기`() {
        assertThat(players.size()).isEqualTo(2)
    }
}
