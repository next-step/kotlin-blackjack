package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardScoreCalculatorTest {
    @Test
    fun `카드점수 최적계산`() {
        var cards1 = listOf(
            Card.of(CardSuit.CLUBS, CardSpell.JACK),
            Card.of(CardSuit.CLUBS, CardSpell.TWO),
            Card.of(CardSuit.CLUBS, CardSpell.ACE),
            Card.of(CardSuit.CLUBS, CardSpell.SEVEN)
        )
        var cards2 = listOf(
            Card.of(CardSuit.CLUBS, CardSpell.ACE),
            Card.of(CardSuit.CLUBS, CardSpell.TEN),
            Card.of(CardSuit.SPADES, CardSpell.ACE)
        )
        var cards3 = listOf(
            Card.of(CardSuit.CLUBS, CardSpell.ACE),
            Card.of(CardSuit.CLUBS, CardSpell.TEN),
            Card.of(CardSuit.CLUBS, CardSpell.TWO),
            Card.of(CardSuit.CLUBS, CardSpell.FOUR),
            Card.of(CardSuit.CLUBS, CardSpell.THREE),
            Card.of(CardSuit.SPADES, CardSpell.ACE)
        )
        var cards4 = listOf(
            Card.of(CardSuit.CLUBS, CardSpell.EIGHT),
            Card.of(CardSuit.CLUBS, CardSpell.TWO),
            Card.of(CardSuit.CLUBS, CardSpell.ACE)
        )

        assertThat(CardScoreCalculator.calculate(cards1)).isEqualTo(Score(20, false))
        assertThat(CardScoreCalculator.calculate(cards2)).isEqualTo(Score(12, false))
        assertThat(CardScoreCalculator.calculate(cards3)).isEqualTo(Score(21, false))
        assertThat(CardScoreCalculator.calculate(cards4)).isEqualTo(Score(21, false))
    }
}
