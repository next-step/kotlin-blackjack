package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ResultTypeTest {
    @DisplayName("점수 비교 결과에 해당하는 ResultType 반환")
    @ParameterizedTest
    @MethodSource("provideAllResultType")
    fun of(score1: Score, score2: Score, expected: ResultType) {
        val actual = ResultType.of(score1, score2)
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideAllResultType() = listOf(
            Arguments.of(Score(21), Score(20), ResultType.BLACKJACK_WIN),
            Arguments.of(Score(19), Score(18), ResultType.WIN),
            Arguments.of(Score(18), Score(18), ResultType.DRAW),
            Arguments.of(Score(18), Score(19), ResultType.LOSE)
        )
    }
}
