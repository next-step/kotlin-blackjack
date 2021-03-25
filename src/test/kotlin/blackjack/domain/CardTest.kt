package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `카드 점수`() {
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.TWO).getDigit()).isEqualTo(2)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.THREE).getDigit()).isEqualTo(3)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.FOUR).getDigit()).isEqualTo(4)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.FIVE).getDigit()).isEqualTo(5)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.SIX).getDigit()).isEqualTo(6)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.SEVEN).getDigit()).isEqualTo(7)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.EIGHT).getDigit()).isEqualTo(8)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.NINE).getDigit()).isEqualTo(9)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.TEN).getDigit()).isEqualTo(10)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.KING).getDigit()).isEqualTo(10)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.JACK).getDigit()).isEqualTo(10)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.QUEEN).getDigit()).isEqualTo(10)
    }
}
