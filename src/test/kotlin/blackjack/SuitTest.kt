package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SuitTest {
    @Test
    internal fun `문양 목록`() {
        // given
        val expected = listOf(
            Suit.SPADES,
            Suit.HEARTS,
            Suit.DIAMONDS,
            Suit.CLUBS
        )

        // when
        val actual = Suit.values()

        // then
        assertThat(actual).containsExactlyElementsOf(expected)
    }
}
