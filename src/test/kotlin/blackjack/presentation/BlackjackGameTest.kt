package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Player
import blackjack.exception.CardExhaustException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackGameTest {

    @Test
    fun `각 플레이어는 시작시 2장의 카드를 받는다`() {
        val players = listOf(
            Player(name = "one"),
            Player(name = "two")
        )

        val actual = BlackjackGame.start(
            players,
            CardsDeck()
        )

        assertEquals(2, actual[0].cards.size)
        assertEquals(2, actual[1].cards.size)
    }

    @Test
    fun `카드를 더 받는다`() {
        val card = BlackjackGame.addCard(
            Player("one"),
            CardsDeck()
        )

        assertNotNull(card)
    }

    @Test
    fun `카드가 모두 소진되었다면 Exception`() {
        assertThrows<CardExhaustException> {
            val cardsDeck = CardsDeck()
            val cardCount = 52

            repeat(cardCount + 1) {
                BlackjackGame.addCard(
                    Player("one"),
                    cardsDeck
                )
            }
        }
    }
}
