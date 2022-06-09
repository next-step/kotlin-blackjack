package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SuitTest {
    @Test
    fun `Enum 으로 Spade, Hart, Diamond, Club이 있다`() {
        assertThat(Suit.values().asList()).isEqualTo(listOf(Suit.Spade, Suit.Heart, Suit.Diamond, Suit.Club))
    }
}
