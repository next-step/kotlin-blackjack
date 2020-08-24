package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest() {
    @Test
    fun make_blackjack() {
        val players = Players(listOf("joo", "han"))

        val blackJack = BlackJack(players)

        assertThat(blackJack.players.players).hasSize(2)
    }

    @Test
    fun ready_game() {
        val players = Players(listOf("joo", "han"))
        val blackJack = BlackJack(players)

        blackJack.readyGame()

        assertThat(blackJack.players.players[0].hands).hasSize(2)
        assertThat(blackJack.players.players[1].hands).hasSize(2)
        assertThat(blackJack.dealer.hands).hasSize(2)
    }

    @Test
    fun betting_players() {
        val players = Players(listOf("joo", "han"))
        val blackJack = BlackJack(players)

        blackJack.bettingMoney { 1_000 }

        assertThat(blackJack.players.players[0].betMoney).isEqualTo(1_000)
        assertThat(blackJack.players.players[1].betMoney).isEqualTo(1_000)
    }
}
