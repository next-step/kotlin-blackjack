package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun make_players() {
        val playerNames = listOf("joo", "hand")

        val players = Players(playerNames)

        assertThat(players.players).hasSize(2)
    }
}
