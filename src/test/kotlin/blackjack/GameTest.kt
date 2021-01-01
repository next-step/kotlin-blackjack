package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class GameTest {

    private lateinit var game: Game
    private lateinit var player: Player

    @BeforeEach
    fun `set up`() {
        game = Game("joseph,jacob")
        player = Player("joseph")
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
        assertThat(game.numberOfPlayers()).isEqualTo(2)
    }

    @Test
    fun `string currently player`() {
        assertThat(game.currentlyPlayer()).isEqualTo(player)
    }

    @Test
    fun `test except chance draw`(){
        assertThrows<IllegalArgumentException> {
            game.chanceDraw("koltin")
        }
    }
}


