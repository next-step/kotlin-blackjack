package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TotalScoreTest {
    @Test
    fun get_total_score() {
        val hands = Hands()
        hands.add(SPADE_JACK)
        hands.add(SPADE_JACK)

        val totalScore = TotalScore.get(hands)

        Assertions.assertThat(totalScore).isEqualTo(20)
    }

    @Test
    fun get_total_score_has_ace() {
        val hands = Hands()
        hands.add(SPADE_JACK)
        hands.add(SPADE_ACE)

        val totalScore = TotalScore.get(hands)

        Assertions.assertThat(totalScore).isEqualTo(21)
    }

    @Test
    fun get_total_score_has_ace_but_ace_calculate_1() {
        val hands = Hands()
        hands.add(SPADE_ACE)
        hands.add(SPADE_JACK)
        hands.add(SPADE_JACK)

        val totalScore = TotalScore.get(hands)

        Assertions.assertThat(totalScore).isEqualTo(21)
    }
}
