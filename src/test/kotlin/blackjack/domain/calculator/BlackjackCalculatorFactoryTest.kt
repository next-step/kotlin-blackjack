package blackjack.domain.calculator

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackCalculatorFactoryTest {
    @Test
    fun `카드의 에이스 여부에 따라 계산기를 다르게 제공한다`() {
        val cards = Cards(
            listOf(
                Card(Denomination.TEN, Suit.CLOVER),
                Card(Denomination.NINE, Suit.SPADE),
                Card(Denomination.ACE, Suit.SPADE)
            )
        )

        assertThat(BlackjackCalculatorFactory.getCalculator(cards)).isSameAs(AceCalculator)
    }

    @Test
    fun `카드의 에이스 여부에 따라 계산기를 다르게 제공한다2`() {
        val cards = Cards(
            listOf(
                Card(Denomination.TEN, Suit.CLOVER),
                Card(Denomination.NINE, Suit.SPADE)
            )
        )

        assertThat(BlackjackCalculatorFactory.getCalculator(cards)).isSameAs(NormalCalculator)
    }
}
