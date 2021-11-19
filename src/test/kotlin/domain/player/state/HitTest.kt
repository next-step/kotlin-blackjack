package domain.player.state

import domain.card.Denomination
import domain.card.PlayingCard
import domain.card.PlayingCards
import domain.card.Suit
import exception.IllegalEarningRate
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class HitTest {
    private val cardList = listOf(
        PlayingCard.of(Denomination.ACE, Suit.SPADES),
        PlayingCard.of(Denomination.ACE, Suit.HEARTS),
        PlayingCard.of(Denomination.KING, Suit.CLUBS)
    )
    private lateinit var hit: Hit

    @BeforeEach
    fun setUp() {
        hit = Hit(PlayingCards(cardList))
    }

    @DisplayName("Hit 상태에서는 earningRate 를 알 수 없다.")
    @Test
    fun illegalEarning() {
        Assertions.assertThatExceptionOfType(IllegalEarningRate::class.java)
            .isThrownBy { hit.earningRate() }
    }

    @DisplayName("Hit 상태에서 score 는 카드 번호의 합이다.")
    @Test
    fun score() {
        val expectedScore = 12
        assertThat(hit.score()).isEqualTo(expectedScore)
    }


    @DisplayName("Hit 에서 stay 하면 Stay 상태로 이동한다.")
    @Test
    fun stay() {
        val state = hit.stay()
        assertAll(
            { assertThat(state.earningRate()).isEqualTo(1.0) },
            { assertThat(state.isFinished()).isTrue }
        )
    }

    @DisplayName("draw 후 숫자가 21을 넘으면 Bust 상태로 이동한다.")
    @Test
    fun bust() {
        val state = hit.draw(PlayingCard.of(Denomination.TEN, Suit.DIAMONDS))
        assertAll(
            { assertThat(state.earningRate()).isEqualTo(-1.0) },
            { assertThat(state.isFinished()).isTrue }
        )
    }

    @DisplayName("draw 후 숫자가 21이 되면 Stay 상태로 이동한다.")
    @Test
    fun finished() {
        val state = hit.draw(PlayingCard.of(Denomination.NINE, Suit.DIAMONDS))
        assertAll(
            { assertThat(state.earningRate()).isEqualTo(1.0) },
            { assertThat(state.isFinished()).isTrue }
        )
    }

    @DisplayName("draw 후 숫자가 21 미만이면 Hit 상태로 이동한다.")
    @Test
    fun hit() {
        val state = hit.draw(PlayingCard.of(Denomination.TWO, Suit.DIAMONDS))
        assertThat(state.isFinished()).isFalse
    }
}
