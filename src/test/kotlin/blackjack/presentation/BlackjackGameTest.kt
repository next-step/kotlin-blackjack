package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `각 플레이어는 시작시 2장의 카드를 받는다`() {
        val game = BlackjackGame()
        val players = listOf(
            Player(name = "one"),
            Player(name = "two")
        )

        val actual = game.start(
            players,
            CardsDeck()
        )

        assertEquals(2, actual[0].cards.size)
        assertEquals(2, actual[1].cards.size)
    }
}
