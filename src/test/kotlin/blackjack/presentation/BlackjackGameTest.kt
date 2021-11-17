package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.exception.CardExhaustException
import blackjack.service.DetermineMatch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackGameTest {

    private val blackjackGame = BlackjackGame(DetermineMatch())

    @Test
    fun `각 플레이어와 딜러는 시작시 2장의 카드를 받는다`() {
        val players = listOf(
            Player(Participant("one")),
            Player(Participant("two"))
        )

        val actual = blackjackGame.start(
            players,
            CardsDeck()
        )

        val actualDealer = actual.dealer.dealer
        val actualPlayers = actual.players.map { it.player }

        assertEquals(2, actualDealer.cards.size)
        assertEquals(2, actualPlayers[0].cards.size)
        assertEquals(2, actualPlayers[1].cards.size)
    }

    @Test
    fun `카드를 더 받는다`() {
        val card = blackjackGame.addCard(
            Player(Participant("one")).player,
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
                blackjackGame.addCard(
                    Player(Participant("one")).player,
                    cardsDeck
                )
            }
        }
    }
}
