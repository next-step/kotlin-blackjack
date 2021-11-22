package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.card.CardsDeck
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParticipantsTest {

    @Test
    fun `build 를 이용해 생성 시 딜러를 포함한 모든 플레이어는 2장의 카드를 받는다`() {
        val dealer = Dealer(Participant("딜러"))
        val one = Player(Participant("one"))
        val two = Player(Participant("two"))

        val participants = Participants.build(
            dealer = dealer,
            players = listOf(one, two),
            cardsDeck = CardsDeck(),
        )

        assertEquals(2, dealer.dealer.cards.size)
        assertEquals(2, one.player.cards.size)
        assertEquals(2, two.player.cards.size)
    }

    @Test
    fun `getRevenueStatistics() 호출시 딜러를 포함한 모든 플레이어의 수익금 통계정보를 가져온다`() {
        val dealer = Dealer(Participant("딜러"))
        val one = Player(Participant("one"))

        dealer.addRevenue(5000.0)
        one.addRevenue(5000.0)

        val participants = Participants(
            dealer = dealer,
            players = listOf(one),
        )

        val revenueStatistics = participants.getRevenueStatistics()

        assertEquals(2, revenueStatistics.size)
        assertEquals(5000.0, revenueStatistics[0].revenue)
        assertEquals("딜러", revenueStatistics[0].playerName)
        assertEquals(5000.0, revenueStatistics[1].revenue)
        assertEquals("one", revenueStatistics[1].playerName)
    }

    @Test
    fun `getReceiveCardStatistics() 호출시 딜러를 포함한 모든 플레이어의 카드정보를 가지고온다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.KING))
        }
        val one = Player(Participant("one")).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.THREE))
        }

        dealer.addRevenue(5000.0)
        one.addRevenue(5000.0)

        val participants = Participants(
            dealer = dealer,
            players = listOf(one),
        )

        val revenueStatistics = participants.getReceiveCardStatistics()

        assertEquals(2, revenueStatistics.size)

        assertEquals(10, revenueStatistics[0].cardSum)
        assertEquals("딜러", revenueStatistics[0].playerName)
        assertEquals(listOf("K CLOVER"), revenueStatistics[0].cardNames)

        assertEquals(3, revenueStatistics[1].cardSum)
        assertEquals("one", revenueStatistics[1].playerName)
        assertEquals(listOf("3 CLOVER"), revenueStatistics[1].cardNames)
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

        val actual = participants.existsBlackjack()

        Assertions.assertTrue(actual)
    }

    @Test
    fun `블랙잭인 인원이 없다면 false`() {
        val dealer = Dealer(Participant("딜러"))
        val players = listOf(Player(Participant("one")))

        val participants = Participants(
            dealer = dealer,
            players = players,
        )

        val actual = participants.existsBlackjack()

        Assertions.assertFalse(actual)
    }
}
