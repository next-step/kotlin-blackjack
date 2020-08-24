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
}
