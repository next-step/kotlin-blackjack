package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandsTest {
    @Test
    fun make_hands() {
        val hands = Hands()

        assertThat(hands.cards).hasSize(0)
    }
}
