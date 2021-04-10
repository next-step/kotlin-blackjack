package blackjack.playingcard

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class SymbolTest {
    @Test
    internal fun `눈의 순서`() {
        // given
        val expected = listOf(
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
            Symbol.KING,
            Symbol.ACE
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
        "KING, 10"
    )
    internal fun `A를 제외한 눈의 카드 값은 다른 값이 무엇이든 간에 상관없이 정해진 값을 가진다`(symbol: Symbol, expectedValue: Int) {
        // when
        val valueWhenSumUnder21 = symbol.valueBy(Value(2))
        val valueWhenSumOver21 = symbol.valueBy(Value(30))

        // then
        assertThat(valueWhenSumUnder21)
            .isEqualTo(valueWhenSumOver21)
            .isEqualTo(Value(expectedValue))
    }

    @ParameterizedTest
    @CsvSource(
        "2,11",
        "10,11",
        "11,1",
        "21,1"
    )
    internal fun `A는 주어진 값과 자기 자신의 값의 합이 21을 초과하면 값을 1로, 21 이하면 값을 11로 판단한다`(sumOthers: Int, expected: Int) {
        // given
        val expectedValue = Value(expected)

        // when
        val actualValue = Symbol.ACE.valueBy(sumOthers = Value(sumOthers))

        // then
        assertThat(actualValue).isEqualTo(expectedValue)
    }
}
