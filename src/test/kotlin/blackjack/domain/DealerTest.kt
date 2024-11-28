package blackjack.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `Dealer는 16점 이하일 때 카드를 받아야 한다`() {
        val dealer = Dealer()
        assertTrue(dealer.needReceiveCard())
    }

    @Test
    fun `Dealer는 16점 이상일 때 카드를 받지 않아야 한다`() {
        val cards = Cards().apply {
            add(Card.of(Suit.HEART, Rank.KING))
            add(Card.of(Suit.HEART, Rank.KING))
        }
        val dealer = Dealer(cards)

        assertFalse(dealer.needReceiveCard())
    }
}
