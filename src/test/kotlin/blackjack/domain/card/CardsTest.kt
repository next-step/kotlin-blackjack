package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드를 가져올 수 있다`() {
        val firstCard = Card.King(CardSuit.CLOVER)
        val secondCard = Card.King(CardSuit.SPADE)
        val cards = Cards(listOf(firstCard, secondCard))

        assertThat(cards[0]).isSameAs(firstCard)
        assertThat(cards[1]).isSameAs(secondCard)
    }

    @Test
    fun `가지고 있는 카드들의 합을 구할 수 있다`() {
        val firstCard = Card.King(CardSuit.CLOVER)
        val secondCard = Card.King(CardSuit.SPADE)
        val cards = Cards(listOf(firstCard, secondCard))

        assertThat(cards.total.value).isEqualTo(20)
    }

    @Test
    fun `한 장의 카드를 추가할 수 있다`() {
        val firstCard = Card.King(CardSuit.CLOVER)
        val secondCard = Card.King(CardSuit.SPADE)
        val cards = Cards(listOf(firstCard, secondCard))

        val additionalCard = Card.Queen(CardSuit.CLOVER)
        cards += additionalCard

        assertThat(cards[0]).isSameAs(firstCard)
        assertThat(cards[1]).isSameAs(secondCard)
        assertThat(cards[2]).isSameAs(additionalCard)
        assertThat(cards.total.value).isEqualTo(30)
    }

    @Test
    fun `모든 플레잉 카드를 가져올 수 있다`() {
        val allCards = Cards.all()
        val totalNumberOfPlayingCards = 52

        assertThat(allCards.size).isEqualTo(totalNumberOfPlayingCards)
    }
}
