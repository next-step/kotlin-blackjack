package blackjack.domain

import blackjack.view.REPLY_HIT
import blackjack.view.REPLY_STAND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var game: Game
    private lateinit var players: Players
    private lateinit var firstPlayer: Player
    private lateinit var secondPlayer: Player

    @BeforeEach
    fun `set up`() {
        game = Game("mark, harry")
        players = game.setUp()
        firstPlayer = players.findPlayer(0)
        secondPlayer = players.findPlayer(1)
    }

    @Test
    fun `set up with two cards`() {
        assertThat(firstPlayer.amountOfCards()).isEqualTo(2)
        assertThat(secondPlayer.amountOfCards()).isEqualTo(2)
    }

    @DisplayName("STAND = 카드 안 받음, HIT = 카드 받되, 기존 점수가 최고점수(21) 이상이면 안 받음")
    @Test
    fun `give chance to draw`() {
        // when
        val firstPlayer = game.giveChanceToDraw(REPLY_STAND)
        val secondPlayer = game.giveChanceToDraw(REPLY_HIT)

        // then
        assertThat(firstPlayer?.amountOfCards()).isEqualTo(2)

        secondPlayer?.let {
            if (!it.hasScoreMoreThanMax()) {
                assertThat(secondPlayer.amountOfCards()).isEqualTo(3)
            }
        }
    }

    @Test
    fun `get current player`() {
        assertTrue(game.currentPlayer() == firstPlayer)
    }

    @Test
    fun `game over when the turn is over`() {
        // when
        game.giveChanceToDraw(REPLY_STAND)
        game.giveChanceToDraw(REPLY_STAND)

        // then
        assertTrue(game.isOver())
    }
}
