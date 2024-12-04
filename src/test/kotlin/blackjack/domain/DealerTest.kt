package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `16점 이하일 경우 카드를 추가로 받는다`() {
        val dealer = Dealer()
        dealer.receive(Deck(listOf(Card(CardRank.ACE, Suit.SPADE), Card(CardRank.FIVE, Suit.HEART))))

        dealer.additionalCard(Card(CardRank.TWO, Suit.DIAMOND))

        assertThat(dealer.score()).isEqualTo(18)
    }

    @Test
    fun `17점 이상일 경우 카드를 추가로 받지 않는다`() {
        val dealer = Dealer()
        dealer.receive(Deck(listOf(Card(CardRank.ACE, Suit.SPADE), Card(CardRank.SIX, Suit.HEART))))

        dealer.additionalCard(Card(CardRank.TWO, Suit.DIAMOND))

        assertThat(dealer.score()).isEqualTo(17)
    }
}