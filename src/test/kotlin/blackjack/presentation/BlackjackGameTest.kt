package blackjack.presentation

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.exception.CardExhaustException
import blackjack.service.DecisionMatch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackGameTest {

    private val blackjackGame = BlackjackGame(DecisionMatch())

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

    @Test
    fun `블랙잭인 인원이 있다면 True`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.TEN))
            addCard(Card(CardPattern.CLOVER, CardDenomination.ACE))
        }
        val players = listOf(Player(Participant("one")))

        val participants = Participants(
            dealer = dealer,
            players = players,
        )

        val actual = blackjackGame.existsBlackjack(participants)

        assertTrue(actual)
    }

    @Test
    fun `블랙잭인 인원이 없다면 false`() {
        val dealer = Dealer(Participant("딜러"))
        val players = listOf(Player(Participant("one")))

        val participants = Participants(
            dealer = dealer,
            players = players,
        )

        val actual = blackjackGame.existsBlackjack(participants)

        assertFalse(actual)
    }
}
