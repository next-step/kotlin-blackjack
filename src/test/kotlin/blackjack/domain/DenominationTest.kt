package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DenominationTest {
    @Test
    fun `점수를 계산한다 2 + 3 + 4 = 9`() {
        val result = Denomination.TWO.calculateScore(
            listOf(
                Denomination.THREE,
                Denomination.FOUR
            )
        )
        assertThat(result).isEqualTo(Score(9))
    }

    @ParameterizedTest
    @MethodSource("provideDenominationsWithAce")
    fun `에이스는 기본적으로 11점이다`(denomination: Denomination, others: List<Denomination>, expected: Score) {
        val result = denomination.calculateScore(others)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideDenominationsWithAceOver21")
    fun `점수 결과가 21이 넘는 경우 에이스는 1점으로 계산한다`(denomination: Denomination, others: List<Denomination>, expected: Score) {
        val result = denomination.calculateScore(others)
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideDenominationsWithAce(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Denomination.TWO, listOf(Denomination.ACE), Score(13)),
                Arguments.of(Denomination.TWO, listOf(Denomination.ACE, Denomination.THREE), Score(16)),
                Arguments.of(Denomination.ACE, listOf(Denomination.TEN), Score(21))
            )
        }

        @JvmStatic
        private fun provideDenominationsWithAceOver21(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Denomination.ACE, listOf(Denomination.ACE), Score(12)),
                Arguments.of(Denomination.TWO, listOf(Denomination.ACE, Denomination.ACE), Score(14)),
                Arguments.of(Denomination.ACE, listOf(Denomination.ACE, Denomination.ACE, Denomination.ACE), Score(14)),
                Arguments.of(Denomination.FIVE, listOf(Denomination.SIX, Denomination.ACE), Score(12))
            )
        }
    }
}
