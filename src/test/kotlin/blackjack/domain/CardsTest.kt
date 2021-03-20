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
        assertThat(cards.bust()).isFalse()
        cards.addCard(Card.of(CardSuit.HEARTS, CardSpell.ACE))
        assertThat(cards.bust()).isTrue()
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

        assertThat(cards.calculate()).isEqualTo(15)
        assertThat(cards2.calculate()).isEqualTo(21)
    }
}
