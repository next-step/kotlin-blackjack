package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SuitTest {

    @Test
    fun `패턴은 4종류를 가지고 있다`() {
        assertThat(Suit.values()).containsExactlyInAnyOrder(
            Suit.CLUB,
            Suit.DIAMOND,
            Suit.HEART,
            Suit.SPADE
        )
    }
}
