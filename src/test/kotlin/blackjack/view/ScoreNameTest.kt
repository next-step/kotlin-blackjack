package blackjack.view

import blackjack.domain.score.ScoreType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreNameTest {
    @Test
    fun `스코어 타입의 이름을 가져올 수 있다`() {
        assertThat(ScoreName.of(ScoreType.WIN)).isEqualTo("승")
        assertThat(ScoreName.of(ScoreType.DRAW)).isEqualTo("무")
        assertThat(ScoreName.of(ScoreType.LOSE)).isEqualTo("패")
    }
}
