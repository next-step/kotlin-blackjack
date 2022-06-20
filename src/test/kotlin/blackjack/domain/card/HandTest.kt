package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class HandTest {
    @Test
    fun `카드를 추가한다`() {
        val hand = Hand()
        hand.add(Card.of(Rank.ACE, Suit.SPADE))
        assertAll({
            assertThat(hand.value).hasSize(1)
            assertThat(hand.value[0].suit).isEqualTo(Suit.SPADE)
            assertThat(hand.value[0].rank).isEqualTo(Rank.ACE)
        })
    }
}
