package blackjack.state

import blackjack.domain.Score
import blackjack.domain.state.GameResultState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultStateTest {

    @Test
    fun `comparedValue가 0이면 무승부이다`() {
        val score1 = Score(12)
        val score2 = Score(12)

        val comparedScoreValue = GameResultState.compareStayScore(score1.compareTo(score2))

        assertThat(comparedScoreValue).isEqualTo(GameResultState.Draw)
    }

    @Test
    fun `comparedValue가 0보다 크면 Win이다`() {
        val score1 = Score(14)
        val score2 = Score(12)

        val comparedScoreValue = GameResultState.compareStayScore(score1.compareTo(score2))

        assertThat(comparedScoreValue).isEqualTo(GameResultState.Win)
    }

    @Test
    fun `comparedValue가 0보다 작으면 Lose이다`() {
        val score1 = Score(12)
        val score2 = Score(14)

        val comparedScoreValue = GameResultState.compareStayScore(score1.compareTo(score2))

        assertThat(comparedScoreValue).isEqualTo(GameResultState.Lose)
    }
}
