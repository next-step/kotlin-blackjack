package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `모든 카드의 점수 총 합을 구할 수 있다`() {
        val pointStrategy = CardPointStrategy { it.value }
        val cards = Cards(
            listOf(
                Card(Suit.Spade, Rank.Ace),
                Card(Suit.Spade, Rank.Two),
            )
        )

        val totalPoint = cards.getPoints(pointStrategy)

        assertThat(totalPoint).isEqualTo(3)
    }
}