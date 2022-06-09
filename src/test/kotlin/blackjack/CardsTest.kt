package blackjack

import blackjack.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드의 합계를 모든 경우의수가 결과가 맞는지 테스트`() {
        val cards1 = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.CLOVER),
                Card(CardNumber.THREE, CardShape.HEART),
                Card(CardNumber.QUEEN, CardShape.CLOVER),
            )
        )

        val answer1 = cards1.possibleResults

        assertThat(answer1.contains(14)).isTrue
        assertThat(answer1.contains(23)).isTrue

        val cards2 = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.CLOVER),
                Card(CardNumber.ACE, CardShape.HEART),
                Card(CardNumber.ACE, CardShape.CLOVER),
            )
        )

        val answer2 = cards2.possibleResults

        assertThat(answer2.contains(3)).isTrue
        assertThat(answer2.contains(12)).isTrue
        assertThat(answer2.contains(21)).isTrue
        assertThat(answer2.contains(30)).isTrue

        val cards3 = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.CLOVER),
                Card(CardNumber.ACE, CardShape.HEART),
                Card(CardNumber.JACK, CardShape.CLOVER),
            )
        )

        val answer3 = cards3.possibleResults

        assertThat(answer3.contains(12)).isTrue
        assertThat(answer3.contains(21)).isTrue
        assertThat(answer3.contains(30)).isTrue
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
