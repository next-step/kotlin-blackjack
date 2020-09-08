package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandsTest {
    @Test
    fun make_hands() {
        val hands = Hands()

        assertThat(hands.cards).hasSize(0)
    }

    @Test
    fun add_card() {
        val hands = Hands()

        hands.add(SPADE_ACE)

        assertThat(hands.cards[0]).isEqualTo(SPADE_ACE)
    }
}
