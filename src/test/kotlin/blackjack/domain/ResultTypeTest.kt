package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ResultTypeTest {
    @DisplayName("점수 비교 결과에 해당하는 ResultType 반환")
    @Test
    fun of() {
        val win = ResultType.of(1)
        val draw = ResultType.of(0)
        val lose = ResultType.of(-1)

        assertAll(
            { assertThat(win).isEqualTo(ResultType.WIN) },
            { assertThat(draw).isEqualTo(ResultType.DRAW) },
            { assertThat(lose).isEqualTo(ResultType.LOSE) },
        )
    }
}
