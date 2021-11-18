package domain.player.state

import domain.card.Denomination
import domain.card.PlayingCard
import domain.card.PlayingCards
import domain.card.Suit
import exception.IllegalDrawException
import exception.IllegalStayException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackjackTest {
    private val cardList = listOf(
        PlayingCard.of(Denomination.KING, Suit.SPADES),
        PlayingCard.of(Denomination.ACE, Suit.HEARTS)
    )
    private val blackjack = Blackjack(Hit(PlayingCards(cardList)))

    @DisplayName("Blackjack 의 earningRate 는 1.5이다.")
    @Test
    fun earningRate() {
        Assertions.assertThat(blackjack.earningRate())
            .isEqualTo(1.5)
    }

    @DisplayName("Blackjack 의 score 는 최대 점수이다.")
    @Test
    fun score() {
        Assertions.assertThat(blackjack.score())
            .isEqualTo(Int.MAX_VALUE)
    }

    @DisplayName("Blackjack 는 finished 상태이다.")
    @Test
    fun isFinished() {
        Assertions.assertThat(blackjack.isFinished()).isTrue
    }

    @DisplayName("Blackjack 상태에서는 stay 가 불가능하다.")
    @Test
    fun illegalBlackjack() {
        Assertions.assertThatExceptionOfType(IllegalStayException::class.java)
            .isThrownBy { blackjack.stay() }
    }

    @DisplayName("Blackjack 상태에서는 draw 가 불가능하다.")
    @Test
    fun illegalDraw() {
        Assertions.assertThatExceptionOfType(IllegalDrawException::class.java)
            .isThrownBy { blackjack.draw(PlayingCard.of(Denomination.ACE, Suit.SPADES)) }
    }
}
