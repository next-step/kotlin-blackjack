package blackjack.state

import blackjack.domain.Money
import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_DRAW_METHOD
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_STAY_METHOD
import blackjack.domain.state.Initial
import blackjack.domain.state.State
import blackjack.domain.strategy.hittable.DealerHittableStrategy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackTest {

    private lateinit var blackjack: Blackjack

    @BeforeEach
    internal fun setUp() {
        blackjack = Blackjack(Cards(listOf(CARD_HEART_ACE, CARD_HEART_TEN)))
    }

    @Test
    fun `Blackjack상태이면 Blackjack과의 매칭시 배팅금액의 0배가 된다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE)))

        val profit: Double = this.blackjack.profit(blackjack, Money.from("3000"))

        assertThat(profit).isEqualTo(0.0)
    }

    @Test
    fun `Blackjack상태이면 bust상태와의 매칭시 배팅금액의 1_5배가 된다`() {
        var bust: State = Initial()
        bust = bust.draw(CARD_HEART_KING)
            .draw(CARD_HEART_KING)
            .draw(CARD_HEART_KING)

        val profit: Double = blackjack.profit(bust, Money.from("3000"))

        assertThat(profit).isEqualTo(4500.0)
    }

    @Test
    fun `Blackjack상태이면 Blackjack상태와의 매칭시 배당률 0이다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE)))

        val earningRate: Double = blackjack.earningRate(blackjack)

        assertThat(earningRate).isEqualTo(0.0)
    }

    @Test
    fun `Blackjack상태이면 bust상태와의 매칭시 배당률은 1_5이다`() {
        var bust: State = Initial()
        bust = bust.draw(CARD_HEART_KING)
            .draw(CARD_HEART_KING)
            .draw(CARD_HEART_KING)

        val earningRate: Double = blackjack.earningRate(bust)

        assertThat(earningRate).isEqualTo(1.5)
    }

    @Test
    fun `Blackjack의 score는 21이다`() {
        val score = blackjack.score

        assertThat(score).isEqualTo(Score(21))
    }

    @Test
    fun `Blackjack은 Finished 상태이다`() {
        val isFinished: Boolean = blackjack.isFinished()

        assertThat(isFinished).isTrue
    }

    @Test
    fun `Blackjack은 카드를 뽑을 수 없다`() {
        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                blackjack.draw(CARD_HEART_ACE)
            }
            .withMessage(UNSUPPORTED_DRAW_METHOD)
    }

    @Test
    fun `Blackjack은 stay할 수 없다`() {
        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                blackjack.stay()
            }.withMessage(UNSUPPORTED_STAY_METHOD)
    }

    @Test
    fun `Blackjack의 canHit()은 False이다`() {
        assertThat(blackjack.canHit(DealerHittableStrategy)).isFalse
    }
}
