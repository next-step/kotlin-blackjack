package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandsTest {
    @Test
    fun make_hands() {
        val hands = Hands()

        assertThat(hands.cards).hasSize(0)
    }

    @Test
    fun add_card() {
        val hands = Hands()

        hands.add(SPADE_ACE)

        assertThat(hands.getFirstCard()).isEqualTo(SPADE_ACE)
    }

    @Test
    fun get_total_score_and_cards_score() {
        val hands = Hands()
        hands.add(SPADE_ACE)

        val totalScore = hands.getTotalScore()
        val cardsScore = hands.getCardsScore()

        assertThat(totalScore).isEqualTo(11)
        assertThat(cardsScore).isEqualTo(1)
    }

    @Test
    fun has_ace() {
        val hands = Hands()
        hands.add(SPADE_ACE)

        assertThat(hands.hasAce()).isTrue()
    }

    @Test
    fun is_black_jack() {
        val hands = Hands()
        hands.add(SPADE_ACE)
        hands.add(SPADE_JACK)

        assertThat(hands.isBlackJack()).isTrue()
    }

    @Test
    fun get_first_card() {
        val hands = Hands()
        hands.add(SPADE_ACE)
        hands.add(SPADE_JACK)

        assertThat(hands.getFirstCard()).isEqualTo(SPADE_ACE)
    }
}
