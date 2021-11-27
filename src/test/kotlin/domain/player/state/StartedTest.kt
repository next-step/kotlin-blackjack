package domain.player.state

import domain.card.Denomination
import domain.card.PlayingCard
import domain.card.PlayingCards
import domain.card.Suit
import exception.IllegalDrawException
import exception.IllegalEarningRate
import exception.IllegalStayException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class StartedTest {
    lateinit var started: Started
    private val cardList = listOf(
        PlayingCard.of(Denomination.JACK, Suit.SPADES),
        PlayingCard.of(Denomination.QUEEN, Suit.HEARTS)
    )

    @BeforeEach
    fun setUp() {
        started = Started(PlayingCards(cardList))
    }

    @DisplayName("Started 상태에서는 earningRate 를 알 수 없다.")
    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun illegalEarning(win: Boolean) {
        assertThatExceptionOfType(IllegalEarningRate::class.java)
            .isThrownBy { started.earningRate(win) }
    }

    @DisplayName("Started 상태에서 score 는 카드 번호의 합이다.")
    @Test
    fun score() {
        val expectedScore = 20
        assertThat(started.score()).isEqualTo(expectedScore)
    }

    @DisplayName("Started 상태에서는 stay 가 불가능하다.")
    @Test
    fun illegalStay() {
        assertThatExceptionOfType(IllegalStayException::class.java)
            .isThrownBy { started.stay() }
    }

    @DisplayName("Started 상태에서는 draw 가 불가능하다.")
    @Test
    fun illegalDraw() {
        assertThatExceptionOfType(IllegalDrawException::class.java)
            .isThrownBy { started.draw(PlayingCard.of(Denomination.ACE, Suit.SPADES)) }
    }

    @DisplayName("Score 가 21 이면 finished 상태로 이동한다.")
    @Test
    fun blackjack() {
        val cardList = listOf(
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.KING, Suit.HEARTS)
        )
        assertThat(Started(PlayingCards(cardList)).nextState().isFinished()).isTrue
    }

    @DisplayName("Score 가 21 미만이면 hit 상태로 이동한다.")
    @Test
    fun hit() {
        val cardList = listOf(
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS)
        )
        assertThat(Started(PlayingCards(cardList)).nextState().isFinished()).isFalse
    }
}
