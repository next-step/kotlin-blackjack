package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    internal fun `총 점수를 계산한다`() {
        // given
        val cardArray = arrayOf(
            Card(Suit.SPADE, Denomination.TWO),
            Card(Suit.SPADE, Denomination.FIVE),
            Card(Suit.SPADE, Denomination.QUEEN)
        )

        // when
        val cards = Cards.of(*cardArray)

        // then
        assertThat(cards.getScore()).isEqualTo(17)
    }

    @Test
    internal fun `Ace가 포함된 총 점수를 계산한다`() {
        // given
        val cardArray = arrayOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.HEART, Denomination.ACE),
            Card(Suit.CLOVER, Denomination.ACE),
            Card(Suit.DIAMOND, Denomination.ACE)
        )

        // when
        val cards = Cards.of(*cardArray)

        // then
        assertThat(cards.getScore()).isEqualTo(14)
    }
}
