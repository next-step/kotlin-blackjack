package domain

import enum.Rank
import enum.Suit
import org.junit.jupiter.api.Assertions.assertEquals
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
}
