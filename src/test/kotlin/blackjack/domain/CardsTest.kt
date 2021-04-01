package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardsTest {

    @Test
    fun `카드 점수 계산하기`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.DIAMOND, Rank.FIVE),
                Card(Suit.HEART, Rank.QUEEN)
            )
        )

        assertThat(cards.calculateScore()).isEqualTo(15)
    }

    @Test
    fun `ACE카드가 포함 된 경우`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.DIAMOND, Rank.ACE),
                Card(Suit.HEART, Rank.QUEEN)
            )
        )

        assertThat(cards.calculateScore()).isEqualTo(21)
    }

    @Test
    fun `ACE카드가 2장 포함된 경우`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.DIAMOND, Rank.ACE),
                Card(Suit.HEART, Rank.FIVE),
                Card(Suit.CLUB, Rank.ACE)
            )
        )

        assertThat(cards.calculateScore()).isEqualTo(17)
    }
}
