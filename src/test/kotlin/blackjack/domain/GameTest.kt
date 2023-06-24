package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {

    private lateinit var game: Game

    @BeforeEach()
    fun setUp() {
        game = Game(NotRandomDeckShuffleStrategy())
    }

    @Test
    fun `게임이 시작되면 플레이어에게 카드를 2장씩 나눠준다`() {
        val pobi = Player("pobi")
        val jason = Player("jason")

        val playerList = listOf(pobi, jason)
        val pobiCards = "A다이아, J다이아"
        val jasonCards = "Q다이아, K다이아"

        game.firstDraw(playerList)

        Assertions.assertThat(pobi.getCards()).isEqualTo(pobiCards)
        Assertions.assertThat(jason.getCards()).isEqualTo(jasonCards)
    }
}
