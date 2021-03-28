package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SymbolTest {
    @Test
    internal fun `눈 목록`() {
        // given
        val expected = listOf(
            Symbol.ACE,
            Symbol.TWO,
            Symbol.THREE,
            Symbol.FOUR,
            Symbol.FIVE,
            Symbol.SIX,
            Symbol.SEVEN,
            Symbol.EIGHT,
            Symbol.NINE,
            Symbol.TEN,
            Symbol.JACK,
            Symbol.QUEEN,
            Symbol.KING
        )

        // when
        val actual = Symbol.values()

        // then
        assertThat(actual).containsExactlyElementsOf(expected)
    }
}
