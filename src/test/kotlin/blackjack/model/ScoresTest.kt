package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ScoresTest {
    @ParameterizedTest
    @MethodSource("scoreListProvider")
    fun `가장 큰 점수와 낮은 점수를 돌려준다`(scores: List<Int>) {
        val result = ScoresFactory.create(scores)

        assertThat(result.size).isEqualTo(scores.size)
        assertThat(result.highest()).isEqualTo(Score(7))
        assertThat(result.lowest()).isEqualTo(Score(1))
    }

    companion object {
        @JvmStatic
        fun scoreListProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf(1, 3, 5, 7)
                    )
                }
            )
        }
    }
}
