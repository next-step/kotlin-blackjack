package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `Dealer는 16점 이하일 때 카드를 받아야 한다`() {
        val dealer = Dealer()
        assertThat(dealer.canDrawCard()).isTrue()
    }

    @Test
    fun `Dealer는 16점 이상일 때 카드를 받지 않아야 한다`() {
        val cards = Cards().apply {
            add(Card.of(Suit.HEART, Rank.KING))
            add(Card.of(Suit.HEART, Rank.KING))
        }
        val dealer = Dealer(cards = cards)

        assertThat(dealer.canDrawCard()).isFalse()
    }

    @Test
    fun `Dealer의 카드 총합이 22점 이상이면 Bust이다`() {
        val cards = Cards().apply {
            add(Card.of(Suit.HEART, Rank.KING))
            add(Card.of(Suit.HEART, Rank.KING))
            add(Card.of(Suit.HEART, Rank.KING))
        }
        val dealer = Dealer(cards = cards)

        assertThat(dealer.isBust()).isTrue()
    }
}
