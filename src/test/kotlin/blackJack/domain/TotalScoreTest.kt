package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TotalScoreTest {
    @Test
    fun get_total_score() {
        val cards = listOf(SPADE_JACK, SPADE_QUEEN)

        val totalScore = TotalScore.get(cards)

        Assertions.assertThat(totalScore).isEqualTo(20)
    }

    @Test
    fun get_total_score_has_ace() {
        val cards = listOf(SPADE_JACK, SPADE_ACE)

        val totalScore = TotalScore.get(cards)

        Assertions.assertThat(totalScore).isEqualTo(21)
    }

    @Test
    fun get_total_score_has_ace_but_ace_calculate_1() {
        val cards = listOf(SPADE_JACK, SPADE_ACE, SPADE_KING)

        val totalScore = TotalScore.get(cards)

        Assertions.assertThat(totalScore).isEqualTo(21)
    }
}
