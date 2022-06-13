package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameScoreTest {

    @Test
    fun `승무패 결과는 1승1무1패`() {
        val gameScore = GameScore()

        gameScore.win()
        gameScore.lose()
        gameScore.draw()

        assertThat(gameScore.win).isEqualTo(1)
        assertThat(gameScore.lose).isEqualTo(1)
        assertThat(gameScore.draw).isEqualTo(1)
    }
}
