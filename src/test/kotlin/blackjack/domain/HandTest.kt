package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HandTest {

    @Test
    fun `주어진 카드를 저장한다`() {
        val card = Card(CardSuitInfo.SPADE, CardNumberInfo.ACE)
        val hand = Hand(listOf(card))

        Assertions.assertThat(hand.size()).isEqualTo(1)
        Assertions.assertThat(hand.toList()).contains(card)
    }

    @Test
    fun `카드들의 숫자 합계를 반환한다`() {
        val cards = listOf(
            Card(CardSuitInfo.SPADE, CardNumberInfo.TEN),
            Card(CardSuitInfo.HEART, CardNumberInfo.TEN),
            Card(CardSuitInfo.DIAMOND, CardNumberInfo.TEN),
        )

        val hand = Hand(cards)
        Assertions.assertThat(hand.sumOf()).isEqualTo(30)
    }

    @Test
    fun `Ace 보유 여부를 반환한다`() {
        val hand = Hand(listOf(Card(CardSuitInfo.SPADE, CardNumberInfo.TEN)))
        Assertions.assertThat(hand.hasAce()).isFalse()

        hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.ACE))
        Assertions.assertThat(hand.hasAce()).isTrue()
    }
}
