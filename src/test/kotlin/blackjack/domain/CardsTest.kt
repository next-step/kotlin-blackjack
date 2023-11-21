package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `Ace는 카드의 총 합이 21을 넘지 않으면 11으로 활용한다`() {
        val cards = Cards(
            listOf(
                Card(Suit.Spade, Rank.Ace),
                Card(Suit.Spade, Rank.Two),
            )
        )

        val totalPoint = cards.getPoints()

        assertThat(totalPoint).isEqualTo(13)
    }

    @Test
    fun `Ace를 11로 사용해서 카드의 총 합이 21을 넘는 경우 1로 활용한다`() {
        val cards = Cards(
            listOf(
                Card(Suit.Spade, Rank.Ace),
                Card(Suit.Spade, Rank.Two),
                Card(Suit.Spade, Rank.Ten),
            )
        )

        val totalPoint = cards.getPoints()

        assertThat(totalPoint).isEqualTo(13)
    }
}