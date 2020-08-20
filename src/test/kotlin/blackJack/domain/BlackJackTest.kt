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

    @Test
    fun player_get_card() {
        val inputValue = "y"
        val blackJack = BlackJack(listOf())
        val player = Player("test")

        val result = blackJack.playerGetCard(player, inputValue)

        assertThat(result).isTrue()
        assertThat(player.getHandsSize()).isEqualTo(1)
    }

    @Test
    fun player_does_not_get_card() {
        val inputValue = "n"
        val blackJack = BlackJack(listOf())
        val player = Player("test")

        val result = blackJack.playerGetCard(player, inputValue)

        assertThat(result).isFalse()
        assertThat(player.getHandsSize()).isEqualTo(0)
    }

    @Test
    fun input_value_is_not_y_or_n() {
        val inputValue = "error"
        val blackJack = BlackJack(listOf())
        val player = Player("test")

        val result = blackJack.playerGetCard(player, inputValue)

        assertThat(result).isNull()
    }
}
