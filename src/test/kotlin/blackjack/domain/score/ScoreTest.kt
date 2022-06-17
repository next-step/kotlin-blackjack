package blackjack.domain.score

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `플레이어 이름을 저장하고 있다`() {
        val name = "vivian"
        assertThat(Score(name, ScoreType.WIN).playerName).isEqualTo(name)
    }

    @Test
    fun `플레이어의 스코어를 저장하고 있다`() {
        val score = ScoreType.WIN
        assertThat(Score("vivian", ScoreType.WIN).score).isEqualTo(score)
    }
}
