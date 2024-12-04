package blackjack.domain

import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `16점 이하일 경우 카드를 추가로 받는다`() {
        val dealer = Dealer()
        dealer.receive(Deck(listOf(Card(CardRank.ACE, Suit.SPADE), Card(CardRank.FIVE, Suit.HEART))))

        dealer.additionalCard(Card(CardRank.TWO, Suit.DIAMOND))

        assert(dealer.score() == 18)
    }
}