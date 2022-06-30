package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HandsTest {
    @Test
    internal fun `ACE 카드가 있는 경우 hasAce = true이다`() {
        val hands = Hands(
            setOf(
                DIAMOND_JACK
            )
        )
        hands.add(DIAMOND_ACE)

        assertThat(hands.hasAce).isTrue
    }

    @Test
    internal fun `ACE 카드가 없는 경우 hasAce = false이다`() {
        val hands = Hands(
            setOf(
                DIAMOND_JACK
            )
        )
        hands.add(DIAMOND_TWO)

        assertThat(hands.hasAce).isFalse
    }
}
