package blackjack

import blackjack.domain.Game
import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class GameTest {

    private lateinit var game: Game
    private lateinit var Joseph: Player
    private lateinit var Jacob: Player

    @BeforeEach
    fun `set up`() {
        game = Game("Joseph,Jacob")
        Joseph = Player("Joseph")
        Jacob = Player("Jacob")
    }

    @Test
    fun `string players`() {
        assertThat(game.allPlayers()).isEqualTo(listOf("Joseph", "Jacob"))
    }

    @Test
    fun `check reduce deck size if give card to player`() {

        assertThat(game.amountOfDeck()).isEqualTo(44)
    }

    @Test
    fun `string currently player`() {
        assertThat(game.currentlyPlayer()).isEqualTo(Joseph)
    }

    @Test
    fun `test except chance draw`() {
        assertThrows<IllegalArgumentException> {
            game.chanceDraw("koltin")
        }
    }

    @Test
    fun `test chance draw`() {
        game.chanceDraw("y")
        assertThat(game.currentlyPlayer()).isEqualTo(Joseph)
        assertThat(game.currentlyPlayer().amountOfCards()).isEqualTo(3)
        game.chanceDraw("n")
        assertThat(game.currentlyPlayer()).isEqualTo(Jacob)
    }
}
