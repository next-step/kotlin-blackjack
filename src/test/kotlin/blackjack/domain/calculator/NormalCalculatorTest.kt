package blackjack.domain.calculator

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NormalCalculatorTest {
    @Test
    fun `ace가 없다면 끗수의 기본 점수를 기반으로 계산한다`() {
        val cards = Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.NINE, Suit.SPADE)))

        assertThat(NormalCalculator.isAssignablefrom(cards)).isTrue()
        assertThat(NormalCalculator.calculate(cards.cards)).isEqualTo(19)
    }
}
