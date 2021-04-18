package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CardsTest {

    @Test
    fun `ACE가 없는 경우 point계산`() {
        val cards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.JACK)))

        val point = cards.calculatePoint()
        Assertions.assertThat(point).isEqualTo(20)
    }

    @Test
    fun `ACE가 있고 21을 넘지 않은 경우 point계산`() {
        val cards = Cards(listOf(Card(CardShape.SPADE, CardType.ACE), Card(CardShape.SPADE, CardType.JACK)))

        val point = cards.calculatePoint()
        Assertions.assertThat(point).isEqualTo(21)
    }

    @Test
    fun `ACE가 1개 있고 21을 넘은 경우 point계산`() {
        val cards = Cards(
            listOf(
                Card(CardShape.SPADE, CardType.ACE),
                Card(CardShape.SPADE, CardType.JACK),
                Card(CardShape.SPADE, CardType.TWO)
            )
        )

        val point = cards.calculatePoint()
        Assertions.assertThat(point).isEqualTo(13)
    }

    @Test
    fun `ACE가 2개 있고 21을 넘은 경우 point계산`() {
        val cards = Cards(
            listOf(
                Card(CardShape.SPADE, CardType.ACE),
                Card(CardShape.HEART, CardType.ACE),
                Card(CardShape.SPADE, CardType.JACK),
                Card(CardShape.SPADE, CardType.TWO)
            )
        )

        val point = cards.calculatePoint()
        Assertions.assertThat(point).isEqualTo(14)
    }
}
