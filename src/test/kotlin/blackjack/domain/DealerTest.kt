@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16 이하이면 반드시 1장의 카드를 추가로 받아야 한다`() {
        val card1 = Card(Denomination.TWO, Suit.SPADE)
        val card2 = Card(Denomination.THREE, Suit.SPADE)
        val dealer = Dealer(cards = Cards(card1, card2))

        val actual = dealer.shouldReceiveCard()

        assertThat(actual).isTrue()
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 17 이상이면 카드를 추가로 받을 수 없다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.SEVEN, Suit.SPADE)
        val dealer = Dealer(cards = Cards(card1, card2))

        val actual = dealer.shouldReceiveCard()

        assertThat(actual).isFalse()
    }
}
