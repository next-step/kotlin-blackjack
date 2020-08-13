package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun make_player() {
        val nameList = listOf("joohan", "pikachu")

        val blackJack = BlackJack(nameList)

        assertThat(blackJack.players.players).hasSize(2)
    }

    @Test
    fun ready_game() {
        val nameList = listOf("joohan", "pikachu")

        val blackJack = BlackJack(nameList)

        assertThat(blackJack.players.players[0].hands).hasSize(2)
        assertThat(blackJack.players.players[1].hands).hasSize(2)
        assertThat(blackJack.dealer.hands).hasSize(2)
    }
}
