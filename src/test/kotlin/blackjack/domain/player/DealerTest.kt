package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `점수 계산 가능`() {
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(dealer.score()).isEqualTo(15)
    }

    @Test
    fun `17보다 작으면 한장을 더 뽑이야한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(dealer.additionalDraw()).isTrue()
    }
}
