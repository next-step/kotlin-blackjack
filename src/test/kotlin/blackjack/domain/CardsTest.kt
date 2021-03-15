package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    private val cards = Cards(arrayListOf(Card.makeCard("A", Pattern.HEART), Card.makeCard("A", Pattern.HEART)))
    private val cards2 = Cards(arrayListOf(Card.makeCard("A", Pattern.HEART), Card.makeCard("J", Pattern.HEART)))
    private val cards3 = Cards(
        arrayListOf(
            Card.makeCard("A", Pattern.HEART),
            Card.makeCard("J", Pattern.HEART),
            Card.makeCard("A", Pattern.SPADE)
        )
    )

    @Test
    fun `카드패에 Ace가 있는경우 가장 마지막으로 정렬되는지 확인`() {
        val sortBeforeLast = cards2.toList().last()
        cards2.calculateCards()
        val sortAfterLast = cards2.toList().last()
        assertThat(sortBeforeLast).isEqualTo(Card.makeCard("J", Pattern.HEART))
        assertThat(sortAfterLast).isEqualTo(Card.makeCard("A", Pattern.HEART))
    }

    @Test
    fun `주어진 카드에서 21과 가장 가깝게 출력하는지 확인`() {
        assertThat(cards.calculateCards()).isEqualTo(22)
        assertThat(cards2.calculateCards()).isEqualTo(21)
        assertThat(cards3.calculateCards()).isEqualTo(22)
    }
}
