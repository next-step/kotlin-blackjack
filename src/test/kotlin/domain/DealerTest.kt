package domain

import enum.Rank
import enum.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    @DisplayName("딜러가 카드를 받으면 손패에 추가되어야 함")
    fun `딜러가 카드를 받을 때`() {
        val dealer = Dealer()
        val card = Card(Suit.HEARTS, Rank.TWO)
        dealer.receiveCard(card)

        assertEquals(listOf(card), dealer.showHand())
    }

    @Test
    @DisplayName("딜러 점수 계산")
    fun `딜러 점수 계산`() {
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEARTS, Rank.KING))
        dealer.receiveCard(Card(Suit.SPADES, Rank.THREE))
        assertEquals(13, dealer.calculateScore())

        dealer.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        assertEquals(14, dealer.calculateScore())
    }

    @Test
    @DisplayName("딜러는 16 이하일 때 카드를 추가로 받는다")
    fun `딜러는 16 이하일 때 카드를 추가로 받는다`() {
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEARTS, Rank.SIX))
        dealer.receiveCard(Card(Suit.CLUBS, Rank.NINE))

        val shouldReceiveMore = dealer.calculateScore() <= BlackjackRules.DEALER_HIT_THRESHOLD
        assertTrue(shouldReceiveMore, "딜러는 16 이하일 때 추가로 카드를 받아야 한다")
    }

    @Test
    @DisplayName("딜러는 17 이상일 때 더 이상 카드를 받지 않는다")
    fun `딜러는 17 이상일 때 더 이상 카드를 받지 않는다`() {
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
        dealer.receiveCard(Card(Suit.CLUBS, Rank.SEVEN))

        val shouldNotReceiveMore = dealer.calculateScore() >= BlackjackRules.DEALER_HIT_THRESHOLD
        assertTrue(shouldNotReceiveMore, "딜러는 17 이상일 때 더 이상 카드를 받지 않아야 한다")
    }
}
