package blackjack

import blackjack.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드의 합계를 모든 경우의수가 결과가 맞는지 테스트`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.CLOVER),
                Card(CardNumber.THREE, CardShape.HEART),
                Card(CardNumber.QUEEN, CardShape.CLOVER),
            )
        )

        val answer = cards.possibleResults

        assertThat(answer.contains(14)).isTrue
        assertThat(answer.contains(23)).isTrue
    }

    @Test
    fun `추가한 카드가 정말 추가되었는지`() {
        val cards = Cards()

        val addCard = Card(CardNumber.ACE, CardShape.CLOVER)
        cards.add(addCard)
        val retrievedCards = cards.cards

        assertThat(retrievedCards.contains(addCard)).isTrue
    }

    @Test
    fun `한번에 여러개 추가한 카드가 정말 추가되었는지`() {
        val cards = Cards()

        val addCards = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.CLOVER),
                Card(CardNumber.SEVEN, CardShape.SPADE),
                Card(CardNumber.JACK, CardShape.HEART)
            )
        )

        cards.addAll(addCards)
        val retrievedCards = cards.cards

        assertThat(retrievedCards.contains(Card(CardNumber.ACE, CardShape.CLOVER))).isTrue
        assertThat(retrievedCards.contains(Card(CardNumber.SEVEN, CardShape.SPADE))).isTrue
        assertThat(retrievedCards.contains(Card(CardNumber.JACK, CardShape.HEART))).isTrue
    }
}
