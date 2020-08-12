package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameTest {

    private val game = Game("mark, harry")

    @Test
    fun `players size`() {
        // when
        val size = game.players.size

        // then
        assertTrue(size == 2)
    }

    @Test
    fun `set up with two cards`() {
        // when
        val players = game.setUp()

        // then
        assertThat(players[0].amountOfCards()).isEqualTo(2)
        assertThat(players[1].amountOfCards()).isEqualTo(2)
    }

    @Test
    fun `draw a card`() {
        // when
        val player = game.giveChanceToDraw(game.players[0])

        // then
        assertThat(player.amountOfCards()).isEqualTo(1)
    }
}
