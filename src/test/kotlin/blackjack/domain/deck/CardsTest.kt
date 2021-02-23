package blackjack.domain.deck

import blackjack.domain.calculator.AceCalculator
import blackjack.domain.calculator.NormalCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardsTest {

    @Test
    fun `보유한 카드 중에 에이스가 있는지 확인한다`() {
        val cards = Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.NINE, Suit.SPADE)))

        assertThat(cards.existAce()).isFalse()
    }

    @Test
    fun `보유한 카드 중에 에이스가 있는지 확인한다2`() {
        val cards = Cards(listOf(Card(Denomination.ACE, Suit.CLOVER), Card(Denomination.NINE, Suit.SPADE)))

        assertThat(cards.existAce()).isTrue()
    }

    @Test
    fun `점수를 계산한다`() {
        val cards = Cards(listOf(Card(Denomination.ACE, Suit.CLOVER), Card(Denomination.NINE, Suit.SPADE)))

        assertThat(cards.calculateScore(AceCalculator)).isEqualTo(20)
    }

    @Test
    fun `점수를 계산한다2`() {
        val cards = Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.NINE, Suit.SPADE)))

        assertThat(cards.calculateScore(NormalCalculator)).isEqualTo(19)
    }

    @Test
    fun `카드를 추가한다`() {
        val cards = Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.NINE, Suit.SPADE)))
        cards.add(Card(Denomination.FIVE, Suit.CLOVER))
        assertThat(cards.cards.size).isEqualTo(3)
    }
}
