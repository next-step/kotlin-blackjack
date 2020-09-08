package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TotalScoreTest {
    private val total20Hands = Hands()
    private val hasAceHands = Hands()

    init {
        total20Hands.add(SPADE_JACK)
        total20Hands.add(SPADE_JACK)
        hasAceHands.add(SPADE_KING)
        hasAceHands.add(SPADE_KING)
        hasAceHands.add(SPADE_ACE)
    }

    @Test
    fun get_total_score() {
        val hands = total20Hands

        val totalScore = TotalScore.get(hands)

        Assertions.assertThat(totalScore).isEqualTo(20)
    }

    @Test
    fun get_total_score_has_ace() {
        val hands = HandsTest().aceJackHands

        val totalScore = TotalScore.get(hands)

        Assertions.assertThat(totalScore).isEqualTo(21)
    }

    @Test
    fun get_total_score_has_ace_but_ace_calculate_1() {
        val hands = hasAceHands

        val totalScore = TotalScore.get(hands)

        Assertions.assertThat(totalScore).isEqualTo(21)
    }
}
