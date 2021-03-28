package blackjack.domain.state

import blackjack.domain.card.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {

    @Test
    fun blackjack() {
        val score = Score(21)
        assertThat(score.isBlackjack).isTrue()
    }

    @Test
    fun bust() {
        val score = Score(23)
        assertThat(score.isBust).isTrue()
    }

    @Test
    fun plusTenIfNotBust() {
        val score = Score(11)
        val actual = score.plusTenIfNotBust()
        assertThat(actual).isEqualTo(Score(21))
    }
}