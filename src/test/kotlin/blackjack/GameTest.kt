package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {

    private lateinit var game: Game
    private lateinit var player: Player

    @BeforeEach
    fun `set up`() {
        game = Game("joseph,jacob")
    }

    @Test
    fun `check Game class with card size`() {

        assertThat(game.amountOfCards()).isEqualTo(4)
    }

    @Test
    fun `check reduce deck size if give card to player`() {

        assertThat(game.amountOfDeck()).isEqualTo(44)
    }

    @Test
    fun `check member of game`() {
        val sampleGame1 = Game("joseph,jacob")
        assertThat(sampleGame1.numberOfPlayers()).isEqualTo(2)
    }
}
