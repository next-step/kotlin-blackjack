package blackjack.domain.calculator

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class AceCalculatorTest {
    @Test
    fun `ace가 있고, 에이스를 포함한 점수가 21점이 넘는다면 1점으로 계산한다`() {
        val cards = Cards(
            listOf(
                Card(Denomination.TEN, Suit.CLOVER),
                Card(Denomination.NINE, Suit.SPADE),
                Card(Denomination.ACE, Suit.SPADE)
            )
        )

        Assertions.assertThat(AceCalculator.isAssignablefrom(cards)).isTrue()
        Assertions.assertThat(AceCalculator.calculate(cards.cards)).isEqualTo(20)
    }

    @Test
    fun `ace가 두장일 때, 한장은 11점 한장은 1점으로 계산`() {
        val cards = Cards(
            listOf(
                Card(Denomination.NINE, Suit.SPADE),
                Card(Denomination.ACE, Suit.SPADE),
                Card(Denomination.ACE, Suit.CLOVER)
            )
        )

        Assertions.assertThat(AceCalculator.isAssignablefrom(cards)).isTrue()
        Assertions.assertThat(AceCalculator.calculate(cards.cards)).isEqualTo(21)
    }
}
