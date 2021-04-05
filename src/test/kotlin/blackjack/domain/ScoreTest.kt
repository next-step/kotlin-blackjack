package blackjack.domain

import blackjack.domain.player.MatchingResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ScoreTest {

    @Test
    fun `Score 는 1점 보다 커야한다 1점 보다 작을 경우 예외`() {
        assertThrows<IllegalArgumentException> { Score(0) }
    }

    @Test
    fun `Score 는 1점 보다 커야한다 1점이거나 큰 경우 생성 성공`() {
        assertDoesNotThrow { Score(1) }
    }

    @Test
    fun `덧셈이 가능하다`() {
        assertThat(Score(1) + Score(2)).isEqualTo(Score(3))
    }

    @Test
    fun `뺄셈이 가능하다`() {
        assertThat(Score(2) - Score(1)).isEqualTo(Score(1))
    }

    @ParameterizedTest(name = " 내점수: {0}, 상대점수: {1} 이면 결과는 {2} 이다")
    @MethodSource("provideMatchingScore")
    fun `점수가 높은쪽이 승리한다`(mine: Score, other: Score, expected: MatchingResult) {
        val result = mine.match(other)
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideMatchingScore(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Score(1), Score(2), MatchingResult.LOSE),
                Arguments.of(Score(2), Score(1), MatchingResult.WIN),
                Arguments.of(Score(2), Score(2), MatchingResult.DRAW)
            )
        }
    }
}
