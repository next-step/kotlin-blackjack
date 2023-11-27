package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드를 받을 수 있다`() {
        // given
        val dealer = Dealer("dealer")
        // when
        dealer.receiveCard(Card(Suit.SPADES, Denomination.ACE))
        // then
        assertEquals("A스페이드", dealer.cards.toString())
    }

    @Test
    fun `처음 받은 카드 합계가 16점 이하일 때 카드 한장을 추가로 받아야 한다`() {
        // given
        val dealer = Dealer("dealer")
        val cardList = listOf(
            Card(Suit.SPADES, Denomination.TEN),
            Card(Suit.SPADES, Denomination.SIX),
        )

        // when
        dealer.receiveInitialCards(cardList)

        // then
        assertEquals(true, dealer.needOneMoreCard())
    }

    @Test
    fun `처음 받은 카드의 합계가 17점 이상이면 카드를 추가로 받을 수 없다`() {
        // given
        val dealer = Dealer("dealer")
        val cardList = listOf(
            Card(Suit.SPADES, Denomination.TEN),
            Card(Suit.SPADES, Denomination.SEVEN),
        )

        // when
        dealer.receiveInitialCards(cardList)

        // then
        assertEquals(false, dealer.needOneMoreCard())
    }
}
