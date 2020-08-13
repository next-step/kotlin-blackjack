package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun makePlayers() {
        val players = Players(listOf(Player("name"), Player("second")))

        assertThat(players.players).hasSize(2)
    }

    @Test
    fun give_card_all_player() {
        val players = Players(listOf(Player("name"), Player("second")))

        players.giveCardAll(Dealer())

        assertThat(players.players[0].hands).hasSize(1)
        assertThat(players.players[1].hands).hasSize(1)
    }
}
