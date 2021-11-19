package domain.player.state

import domain.card.Denomination
import domain.card.PlayingCard
import domain.card.PlayingCards
import domain.card.Suit
import exception.IllegalDrawException
import exception.IllegalStayException
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StayTest {
    private val cardList = listOf(
        PlayingCard.of(Denomination.ACE, Suit.SPADES),
        PlayingCard.of(Denomination.ACE, Suit.HEARTS),
        PlayingCard.of(Denomination.ACE, Suit.CLUBS)
    )
    private val stay = Stay(Hit(PlayingCards(cardList)))

    @DisplayName("Stay 의 earningRate 는 1이다.")
    @Test
    fun earningRate() {
        assertThat(stay.earningRate())
            .isEqualTo(1.0)
    }

    @DisplayName("Stay 의 score 는 카드들의 숫자의 합이다.")
    @Test
    fun score() {
        assertThat(stay.score())
            .isEqualTo(13)
    }

    @DisplayName("Stay 는 Finished 상태이다.")
    @Test
    fun isFinished() {
        assertThat(stay.isFinished()).isTrue
    }

    @DisplayName("Stay 상태에서는 stay 가 불가능하다.")
    @Test
    fun illegalStay() {
        Assertions.assertThatExceptionOfType(IllegalStayException::class.java)
            .isThrownBy { stay.stay() }
    }

    @DisplayName("Stay 상태에서는 draw 가 불가능하다.")
    @Test
    fun illegalDraw() {
        Assertions.assertThatExceptionOfType(IllegalDrawException::class.java)
            .isThrownBy { stay.draw(PlayingCard.of(Denomination.ACE, Suit.SPADES)) }
    }
}
