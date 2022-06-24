package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ScoreTest {

    @Test
    fun `Score 덧셈 확인`() {
        assertThat(Score(10) + Score(20)).isEqualTo(Score(30))
    }

    @Test
    fun `isEqual 확인`() {
        assertThat(Score(10) == Score(10)).isEqualTo(true)
    }

    @Test
    fun `Score 초과 비교 확인`() {
        assertThat(Score(10).isGreater(Score(5))).isEqualTo(true)
    }
}
