package blackjack.service

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DetermineMatchTest {

    private val determineMatch = DetermineMatch()

    @Test
    fun `참가자가 첫 카드가 블랙잭이고 딜러는 블랙잭이 아닐 때 배팅금액의 150% 를 받는다`() {
        val dealer = Dealer(dealer = Participant("딜러"))
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.TEN))
            addCard(Card(CardPattern.CLOVER, CardDenomination.ACE))
        }

        determineMatch.matchWhenFirstCardBlackjack(
            dealer,
            listOf(player)
        )

        assertEquals(50.0, player.getRevenue())
        assertEquals(-50.0, dealer.getRevenue())
    }

    @Test
    fun `참가자가 첫 카드가 블랙잭이고 딜러도 블랙잭일 때 배팅금액만 돌려받는다`() {
        val dealer = Dealer(dealer = Participant("딜러")).apply {
            addCard(Card(CardPattern.HEART, CardDenomination.TEN))
            addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        }
        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.TEN))
            addCard(Card(CardPattern.CLOVER, CardDenomination.ACE))
        }

        determineMatch.matchWhenFirstCardBlackjack(
            dealer,
            listOf(player)
        )

        assertEquals(0.0, player.getRevenue())
        assertEquals(0.0, dealer.getRevenue())
    }

    @Test
    fun `참가자가 첫 카드가 블랙잭이 아니고 딜러는 블랙잭이 아닐 때 배팅금액을 잃는다`() {
        val dealer = Dealer(dealer = Participant("딜러")).apply {
            addCard(Card(CardPattern.HEART, CardDenomination.TEN))
            addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        }
        val player = Player(Participant("one"), 100.0)

        determineMatch.matchWhenFirstCardBlackjack(
            dealer,
            listOf(player)
        )

        assertEquals(-100.0, player.getRevenue())
        assertEquals(100.0, dealer.getRevenue())
    }

    @Test
    fun `현재 가지고 있는 카드가 21을 초과할때 살아남은 플레이어들에게 모두 패배한다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.TEN))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
            addCard(Card(CardPattern.HEART, CardDenomination.KING))
        }

        val one = Player(Participant("one"), 100.0)
        val two = Player(Participant("two"), 150.0)
        val players = listOf(one, two)

        determineMatch.match(dealer, players)

        assertEquals(100.0, one.getRevenue())
        assertEquals(150.0, two.getRevenue())
        assertEquals(-250.0, dealer.getRevenue())
    }

    @Test
    fun `현재 가지고 있는 카드가 플레이어의 카드보다 클경우 승리한다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
            addCard(Card(CardPattern.HEART, CardDenomination.KING))
        }

        val one = Player(Participant("one"), 100.0)
        val players = listOf(one)

        determineMatch.match(dealer, players)

        assertEquals(100.0, dealer.getRevenue())
        assertEquals(-100.0, one.getRevenue())
    }

    @Test
    fun `현재 가지고 있는 카드가 플레이어의 카드보다 작을경우 패배한다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.TWO))
        }

        val player = Player(Participant("one"), 100.0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.THREE))
        }

        val players = listOf(player)

        determineMatch.match(dealer, players)

        assertEquals(100.0, player.getRevenue())
        assertEquals(-100.0, dealer.getRevenue())
    }

    @Test
    fun `딜러의 카드합이 18, 플레이어1의 카드합이 19, 플레이어2의 카드합이 17, 플레이어3의 카드합이 18일때 1승 1패 1무`() {
        val dealer = buildDealer()
        val one = Player(Participant("one"), 100.0).apply {
            addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.NINE))
        }

        val two = Player(Participant("one"), 150.0).apply {
            addCard(Card(pattern = CardPattern.SPADE, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.SPADE, denomination = CardDenomination.SEVEN))
        }

        val three = Player(Participant("one"), 50.0).apply {
            addCard(Card(pattern = CardPattern.CLOVER, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.CLOVER, denomination = CardDenomination.EIGHT))
        }

        determineMatch.match(dealer, listOf(one, two, three))

        assertEquals(100.0, one.getRevenue())
        assertEquals(-150.0, two.getRevenue())
        assertEquals(0.0, three.getRevenue())
        assertEquals(50.0, dealer.getRevenue())
    }

    @Test
    fun `딜러의 카드합이 18, 플레이어1의 카드합이 22일 때 승리한다`() {
        val dealer = buildDealer()
        val one = Player(Participant("one"), 100.0).apply {
            addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.TWO))
        }

        determineMatch.match(dealer, listOf(one))

        assertEquals(-100.0, one.getRevenue())
        assertEquals(100.0, dealer.getRevenue())
    }

    private fun buildDealer(): Dealer {
        return Dealer(Participant("딜러")).apply {
            addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.EIGHT))
        }
    }
}
