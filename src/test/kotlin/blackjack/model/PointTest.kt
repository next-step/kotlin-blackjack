package blackjack.model

import blackjack.dto.Number
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PointTest {

    @ParameterizedTest
    @MethodSource("pointCalcCandidate")
    fun `점수를 구한다`(numbers: List<Number>, expected: Int) {
        assertThat(Point(numbers).getPoint()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun pointCalcCandidate(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(Number.ACE, Number.TWO, Number.THREE),
                    16
                ),
                Arguments.of(
                    listOf(Number.ACE, Number.TWO, Number.THREE, Number.FOUR),
                    20
                ),
                Arguments.of(
                    listOf(Number.ACE, Number.TWO, Number.THREE, Number.FOUR, Number.FIVE),
                    15
                ),
                Arguments.of(
                    listOf(Number.ACE, Number.TWO, Number.THREE, Number.FOUR, Number.FIVE, Number.SIX),
                    21
                ),
                Arguments.of(
                    listOf(Number.JACK, Number.QUEEN, Number.KING),
                    30
                ),
                Arguments.of(
                    listOf(Number.ACE, Number.JACK, Number.QUEEN, Number.KING),
                    31
                ),
                Arguments.of(
                    listOf(Number.ACE, Number.ACE, Number.ACE, Number.ACE),
                    14
                ),
            )
        }
    }
}
