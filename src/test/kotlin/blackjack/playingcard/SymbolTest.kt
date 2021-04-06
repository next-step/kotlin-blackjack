package blackjack.playingcard

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

    @ParameterizedTest(name = "{0}의 이니셜은 {1}이다")
    @CsvSource(
        "ACE, A",
        "TWO, 2",
        "THREE, 3",
        "FOUR, 4",
        "FIVE, 5",
        "SIX, 6",
        "SEVEN, 7",
        "EIGHT, 8",
        "NINE, 9",
        "TEN, 10",
        "JACK, J",
        "QUEEN, Q",
        "KING, K"
    )
    internal fun `눈은 이니셜을 가진다`(symbol: Symbol, expectedInitial: String) {
        // when
        val actualInitial = symbol.initial

        // then
        assertThat(actualInitial).isEqualTo(expectedInitial)
    }

    @ParameterizedTest(name = "{0}의 값은 {1}이다")
    @CsvSource(
        "TWO, 2",
        "THREE, 3",
        "FOUR, 4",
        "FIVE, 5",
        "SIX, 6",
        "SEVEN, 7",
        "EIGHT, 8",
        "NINE, 9",
        "TEN, 10",
        "JACK, 10",
        "QUEEN, 10",
        "KING, 10",
        "ACE, 11"
    )
    internal fun `눈은 카드 값을 가진다`(symbol: Symbol, value: Int) {
        // when
        val actualInitial = symbol.value

        // then
        assertThat(actualInitial).isEqualTo(Value(value))
    }
}
