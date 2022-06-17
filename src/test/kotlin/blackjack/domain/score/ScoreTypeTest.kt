package blackjack.domain.score

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class ScoreTypeTest {
    @ParameterizedTest
    @EnumSource(ScoreType::class)
    fun `스코어 타입에는 승, 패, 무가 있다`(scoreType: ScoreType) {
        val expectedScoreTypes = listOf(ScoreType.WIN, ScoreType.DRAW, ScoreType.LOSE)

        assertThat(scoreType).isIn(expectedScoreTypes)
    }

    @Test
    fun `반대되는 스코어 타입을 구할 수 있다`() {
        assertThat(ScoreType.WIN.opposite()).isEqualTo(ScoreType.LOSE)
        assertThat(ScoreType.DRAW.opposite()).isEqualTo(ScoreType.DRAW)
        assertThat(ScoreType.LOSE.opposite()).isEqualTo(ScoreType.WIN)
    }
}
