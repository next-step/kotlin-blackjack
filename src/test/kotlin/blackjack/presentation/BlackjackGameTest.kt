package blackjack.presentation

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.ResultStatus
import blackjack.exception.CardExhaustException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackGameTest {

    @Test
    fun `각 플레이어와 딜러는 시작시 2장의 카드를 받는다`() {
        val players = listOf(
            Player(name = "one"),
            Player(name = "two")
        )

        val actual = BlackjackGame.start(
            players,
            CardsDeck()
        )

        val actualDealer = actual.dealer.dealer
        val actualPlayers = actual.guest.map { it.player }

        assertEquals(2, actualDealer.cards.size)
        assertEquals(2, actualPlayers[0].cards.size)
        assertEquals(2, actualPlayers[1].cards.size)
    }

    @Test
    fun `카드를 더 받는다`() {
        val card = BlackjackGame.addCard(
            Player("one").player,
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
                    Player("one").player,
                    cardsDeck
                )
            }
        }
    }

    @Test
    fun `딜러의 카드합이 18, 플레이어1의 카드합이 19, 플레이어2의 카드합이 17, 플레이어3의 카드합이 18일때 1승 1패 1무`() {
        val players = buildPlayers()

        BlackjackGame.match(players)

        val matchResult = players.dealer.getMatchResult()
        val guest = players.guest

        assertEquals(1, matchResult[ResultStatus.WIN])
        assertEquals(1, matchResult[ResultStatus.LOSE])
        assertEquals(1, matchResult[ResultStatus.TIE])

        assertEquals(ResultStatus.WIN, guest[0].resultStatus)
        assertEquals(ResultStatus.LOSE, guest[1].resultStatus)
        assertEquals(ResultStatus.TIE, guest[2].resultStatus)
    }

    private fun buildPlayers(): Players {
        val dealer = Dealer()
        dealer.dealer.addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.TEN))
        dealer.dealer.addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.EIGHT))

        val one = Player("one")
        one.player.addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.TEN))
        one.player.addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.NINE))

        val two = Player("one")
        two.player.addCard(Card(pattern = CardPattern.SPADE, denomination = CardDenomination.TEN))
        two.player.addCard(Card(pattern = CardPattern.SPADE, denomination = CardDenomination.SEVEN))

        val three = Player("one")
        three.player.addCard(Card(pattern = CardPattern.CLOVER, denomination = CardDenomination.TEN))
        three.player.addCard(Card(pattern = CardPattern.CLOVER, denomination = CardDenomination.EIGHT))

        return Players(
            dealer = dealer,
            guest = listOf(one, two, three)
        )
    }
}
