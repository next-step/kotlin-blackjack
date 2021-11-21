package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.card.CardsDeck
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `현재 가지고 있는 카드의 총합이 16이하면 카드를 한장 더 받는다`() {
        val dealer = Dealer(Participant("딜러"))

        val card = dealer.addCardWhenLessThanStandard(CardsDeck())

        assertNotNull(card)
    }

    @Test
    fun `현재 가지고 있는 카드의 총합이 17이상이면 카드를 받지 않는다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }

        val card = dealer.addCardWhenLessThanStandard(CardsDeck())

        assertNull(card)
    }

    @Test
    fun `첫 카드의 결과가 플레이어는 블랙잭, 딜러는 블랙잭이 아닐경우 딜러는 배팅금액의 50%를 플레이어게 준다`() {
        val dealer = Dealer(Participant("딜러"))
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }

        dealer.matchWhenFirstCardBlackjack(player)

        assertEquals(50.0, player.getRevenue())
        assertEquals(-50.0, dealer.getRevenue())
    }

    @Test
    fun `첫 카드의 결과가 딜러는 블랙잭, 플레이어는 블랙잭이 아닐경우 플레이어는 배팅금액의 100%를 딜러에게 준다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }
        val player = Player(Participant("one"), 100.0)

        dealer.matchWhenFirstCardBlackjack(player)

        assertEquals(100.0, dealer.getRevenue())
        assertEquals(-100.0, player.getRevenue())
    }

    @Test
    fun `첫 카드의 결과가 둘다 블랙잭일 경우에는 배팅금액을 돌려준다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }

        dealer.matchWhenFirstCardBlackjack(player)

        assertEquals(0.0, dealer.getRevenue())
        assertEquals(0.0, player.getRevenue())
    }

    @Test
    fun `최종 승패를 가릴 때 플레이어가 21보다 크다면 배팅금액을 딜러에게 준다`() {
        val dealer = Dealer(Participant("딜러"))
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.TWO))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }

        dealer.match(player)

        assertEquals(100.0, dealer.getRevenue())
        assertEquals(-100.0, player.getRevenue())
    }

    @Test
    fun `최종 승패를 가릴 때 딜러의 카드가 플레이어의 카드보다 크다면 배팅금액을 딜러에게 준다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.THREE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.TWO))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }

        dealer.match(player)

        assertEquals(100.0, dealer.getRevenue())
        assertEquals(-100.0, player.getRevenue())
    }

    @Test
    fun `최종 승패를 가릴 때 플레이어의 카드가 딜러의 카드보다 크다면 배팅금액을 딜러에게 받는다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.THREE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.FOUR))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }

        dealer.match(player)

        assertEquals(100.0, player.getRevenue())
        assertEquals(-100.0, dealer.getRevenue())
    }

    @Test
    fun `최종 승패를 가릴 때 딜러의 카드가 21보다 크다면 배팅금액만큼 딜러에게 받는다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.THREE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.FOUR))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }

        dealer.match(player)

        assertEquals(100.0, player.getRevenue())
        assertEquals(-100.0, dealer.getRevenue())
    }

    @Test
    fun `최종 승패를 가릴 때 딜러의 카드와 플레이어의 카드가 같다면 배팅금액을 돌려받는다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.QUEEN))
        }

        dealer.match(player)

        assertEquals(0.0, player.getRevenue())
        assertEquals(0.0, dealer.getRevenue())
    }
}
