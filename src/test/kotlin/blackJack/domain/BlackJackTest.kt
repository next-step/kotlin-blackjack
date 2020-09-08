package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest() {
    @Test
    fun make_black_jack() {
        val blackJack = BlackJack(Players(listOf("joo", "han")))

        assertThat(blackJack.dealer.hands.cards).hasSize(2)
        assertThat(blackJack.players.players[0].hands.cards).hasSize(2)
        assertThat(blackJack.players.players[1].hands.cards).hasSize(2)
    }

    @Test
    fun player_hit() {
        val blackJack = BlackJack(Players(listOf("joo", "han")))
        val player = Player("test")

        blackJack.giveCard(true, player)

        assertThat(player.hands.cards).hasSize(1)
    }

    @Test
    fun player_stay() {
        val blackJack = BlackJack(Players(listOf("joo", "han")))
        val player = Player("test")

        blackJack.giveCard(false, player)

        assertThat(player.hands.cards).hasSize(0)
    }
}
