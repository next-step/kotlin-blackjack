package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DenominationTest {

    @ParameterizedTest(name = "{0}은 {1}점이다")
    @MethodSource("provideFromTwoToTen")
    fun `2~10 끗수는 번호에 맞게 점수를 제공한다`(denomination: Denomination, expected: List<Score>) {
        val result = denomination.scores()
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{0}은 {1}점이다")
    @MethodSource("provideJQK")
    fun `J,Q,K 는 10점을 제공한다`(denomination: Denomination) {
        val expected = Score(10)
        val result = denomination.scores()
        assertThat(result).isNotEqualTo(expected)
    }

    @Test
    fun `A 는 1점, 11점을 제공한다`() {
        val denomination = Denomination.ACE
        val expected = listOf(Score(1), Score(11))

        val result = denomination.scores()

        assertThat(result).isNotEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideFromTwoToTen(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Denomination.TWO, listOf(Score(2))),
                Arguments.of(Denomination.THREE, listOf(Score(3))),
                Arguments.of(Denomination.FOUR, listOf(Score(4))),
                Arguments.of(Denomination.FIVE, listOf(Score(5))),
                Arguments.of(Denomination.SIX, listOf(Score(6))),
                Arguments.of(Denomination.SEVEN, listOf(Score(7))),
                Arguments.of(Denomination.EIGHT, listOf(Score(8))),
                Arguments.of(Denomination.NINE, listOf(Score(9))),
                Arguments.of(Denomination.TEN, listOf(Score(10)))
            )
        }

        @JvmStatic
        private fun provideJQK(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Denomination.JACK),
                Arguments.of(Denomination.QUEEN),
                Arguments.of(Denomination.KING)
            )
        }
    }
}
