package blackjack.card

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드를 가져올 수 있다`() {
        val firstCard = Card.King(CardSuit.CLOVER)
        val secondCard = Card.King(CardSuit.SPADE)
        val cards = Cards(listOf(firstCard, secondCard))

        assertThat(cards.getCardAt(0)).isSameAs(firstCard)
        assertThat(cards.getCardAt(1)).isSameAs(secondCard)
    }

    @Test
    fun `가지고 있는 카드들의 합을 구할 수 있다`() {
        val firstCard = Card.King(CardSuit.CLOVER)
        val secondCard = Card.King(CardSuit.SPADE)
        val cards = Cards(listOf(firstCard, secondCard))

        val expectedTotal = 10 + 10

        assertThat(cards.total.value).isEqualTo(expectedTotal)
    }

    @Test
    fun `한 장의 카드를 추가할 수 있다`() {
        val firstCard = Card.King(CardSuit.CLOVER)
        val secondCard = Card.King(CardSuit.SPADE)
        val cards = Cards(listOf(firstCard, secondCard))

        val additionalCard = Card.Queen(CardSuit.CLOVER)
        cards += additionalCard

        val expectedTotal = 10 + 10 + 10

        assertThat(cards.getCardAt(0)).isSameAs(firstCard)
        assertThat(cards.getCardAt(1)).isSameAs(secondCard)
        assertThat(cards.getCardAt(2)).isSameAs(additionalCard)
        assertThat(cards.total.value).isEqualTo(expectedTotal)
    }
}
