package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandsTest {
    private val aceHands = Hands()
    val aceJackHands = Hands()
    init {
        aceHands.add(SPADE_ACE)
        aceJackHands.add(SPADE_ACE)
        aceJackHands.add(SPADE_JACK)
    }

    @Test
    fun make_hands() {
        val hands = Hands()

        assertThat(hands.cards).hasSize(0)
    }

    @Test
    fun add_card() {
        val hands = Hands()

        hands.add(SPADE_KING)

        assertThat(hands.getFirstCard()).isEqualTo(SPADE_KING)
    }

    @Test
    fun get_total_score_and_cards_score() {
        val hands = aceHands

        val totalScore = hands.getTotalScore()
        val cardsScore = hands.getCardsScore()

        assertThat(totalScore).isEqualTo(11)
        assertThat(cardsScore).isEqualTo(1)
    }

    @Test
    fun has_ace() {
        val hands = aceHands

        assertThat(hands.hasAce()).isTrue()
    }

    @Test
    fun is_black_jack() {
        val hands = aceJackHands

        assertThat(hands.isBlackJack()).isTrue()
    }

    @Test
    fun get_first_card() {
        val hands = aceJackHands

        assertThat(hands.getFirstCard()).isEqualTo(SPADE_ACE)
    }
}
