package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardsTest {
    @Test
    fun `카드 추가 & 버스트`() {
        val cards = Cards.from(
            listOf(
                Card.of(CardSuit.CLUBS, CardSpell.TEN),
                Card.of(CardSuit.CLUBS, CardSpell.JACK)
            )
        )

        cards.addCard(Card.of(CardSuit.CLUBS, CardSpell.ACE))
        assertThat(cards.isBust()).isFalse()
        cards.addCard(Card.of(CardSuit.HEARTS, CardSpell.ACE))
        assertThat(cards.isBust()).isTrue()
    }

    @Test
    fun `카드 총합 계산`() {
        val cards = Cards.from(
            listOf(
                Card.of(CardSuit.CLUBS, CardSpell.QUEEN),
                Card.of(CardSuit.CLUBS, CardSpell.TWO),
                Card.of(CardSuit.CLUBS, CardSpell.THREE)
            )
        )
        val cards2 = Cards.from(
            listOf(
                Card.of(CardSuit.CLUBS, CardSpell.TEN),
                Card.of(CardSuit.CLUBS, CardSpell.JACK),
                Card.of(CardSuit.CLUBS, CardSpell.ACE)
            )
        )

        val cards3 = Cards.from(
            listOf(
                Card.of(CardSuit.CLUBS, CardSpell.ACE),
                Card.of(CardSuit.CLUBS, CardSpell.TEN),
                Card.of(CardSuit.CLUBS, CardSpell.JACK)
            )
        )

        assertThat(cards.calculate()).isEqualTo(Score(15))
        assertThat(cards2.calculate()).isEqualTo(Score(21))
        assertThat(cards3.calculate()).isEqualTo(Score(21))
    }
}
