package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ResultTypeTest {
    @DisplayName("점수 비교 결과에 해당하는 ResultType 반환")
    @ParameterizedTest
    @MethodSource("provideAllResultType")
    fun of(compareResult: Int, expected: ResultType) {
        val actual = ResultType.of(compareResult)
        assertThat(actual).isEqualTo(expected)
    }

    fun provideAllResultType() = listOf(
        Arguments.of(1, ResultType.WIN),
        Arguments.of(0, ResultType.DRAW),
        Arguments.of(-1, ResultType.LOSE)
    )
}
