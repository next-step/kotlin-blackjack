package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {

    private lateinit var game: Game
    private lateinit var player: Player

    @Test
    fun `check Game class with card size`() {
        val sampleGame = Game("joseph,jacob")
        assertThat(sampleGame.amountOfCards()).isEqualTo(4)
    }
}
