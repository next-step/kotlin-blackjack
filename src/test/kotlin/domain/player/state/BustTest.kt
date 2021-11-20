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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BustTest {
    private val cardList = listOf(
        PlayingCard.of(Denomination.KING, Suit.SPADES),
        PlayingCard.of(Denomination.QUEEN, Suit.HEARTS),
        PlayingCard.of(Denomination.JACK, Suit.CLUBS)
    )
    private val bust = Bust(Hit(PlayingCards(cardList)))

    @DisplayName("Bust 의 earningRate 는 -1이다.")
    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun earningRate(win: Boolean) {
        Assertions.assertThat(bust.earningRate(win))
            .isEqualTo(-1.0)
    }

    @DisplayName("Bust 의 score 는 최저 점수이다.")
    @Test
    fun score() {
        Assertions.assertThat(bust.score())
            .isEqualTo(Int.MIN_VALUE)
    }

    @DisplayName("Bust 는 Finished 상태이다.")
    @Test
    fun isFinished() {
        Assertions.assertThat(bust.isFinished()).isTrue
    }

    @DisplayName("Bust 상태에서는 stay 가 불가능하다.")
    @Test
    fun illegalBust() {
        Assertions.assertThatExceptionOfType(IllegalStayException::class.java)
            .isThrownBy { bust.stay() }
    }

    @DisplayName("Bust 상태에서는 draw 가 불가능하다.")
    @Test
    fun illegalDraw() {
        Assertions.assertThatExceptionOfType(IllegalDrawException::class.java)
            .isThrownBy { bust.draw(PlayingCard.of(Denomination.ACE, Suit.SPADES)) }
    }
}
