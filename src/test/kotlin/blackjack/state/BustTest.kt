package blackjack.state

import blackjack.domain.Money
import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished
import blackjack.domain.state.Stay
import blackjack.domain.strategy.hittable.DealerHittableStrategy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BustTest {

    private lateinit var bust: Bust

    @BeforeEach
    internal fun setUp() {
        bust = Bust(Cards(listOf(CARD_HEART_TEN, CARD_HEART_TEN, CARD_HEART_KING)))
    }

    @Test
    fun `Bust 상태이면 Bust와의 profit()에서 0배 곱해진다`() {
        val bust = Bust(Cards(listOf(CARD_HEART_KING, CARD_HEART_TEN, CARD_HEART_KING)))

        val profit: Double = this.bust.profit(bust, Money.from("3000"))

        assertThat(profit).isEqualTo(0.0)
    }

    @Test
    fun `Bust 상태이면 stay상태와의 profit()에서 -1배 곱해진다`() {
        val stay = Stay(Cards(listOf(CARD_HEART_ACE, CARD_HEART_TWO)))
        val profit: Double = bust.profit(stay, Money.from("3000"))

        assertThat(profit).isEqualTo(-3000.0)
    }

    @Test
    fun `Bust 상태이면 Blackjack 상태와의 profit()에서 -1배 곱해진다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE)))
        val profit: Double = bust.profit(blackjack, Money.from("3000"))

        assertThat(profit).isEqualTo(-3000.0)
    }

    @Test
    fun `Bust 상태이면 Bust와의 earningRate는 0이다`() {
        val bust = Bust(Cards(listOf(CARD_HEART_KING, CARD_HEART_TEN, CARD_HEART_KING)))

        val earningRate: Double = this.bust.earningRate(bust)

        assertThat(earningRate).isEqualTo(0.0)
    }

    @Test
    fun `Bust 상태이면 Stay와의 earningRate는 -1이다`() {
        val stay = Stay(Cards(listOf(CARD_HEART_ACE, CARD_HEART_TWO)))

        val earningRate: Double = this.bust.earningRate(stay)

        assertThat(earningRate).isEqualTo(-1.0)
    }

    @Test
    fun `Bust 상태이면 Blackjack과의 earningRate는 -1이다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE)))

        val earningRate: Double = this.bust.earningRate(blackjack)

        assertThat(earningRate).isEqualTo(-1.0)
    }

    @Test
    fun `Bust는 finished 상태이다`() {
        val isFinished: Boolean = bust.isFinished()

        assertThat(isFinished).isTrue
    }

    @Test
    fun `Bust는 카드를 뽑을 수 없다`() {

        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy {
            bust.draw(CARD_HEART_ACE)
        }.withMessage(Finished.UNSUPPORTED_DRAW_METHOD)
    }

    @Test
    fun `Bust는 stay할 수 없다`() {

        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy {
            bust.stay()
        }.withMessage(Finished.UNSUPPORTED_STAY_METHOD)
    }

    @Test
    fun `Bust된 score를 알 수 있다`() {
        assertThat(bust.score).isEqualTo(Score(30))
    }

    @Test
    fun `Bust의 canHit()은 False이다`() {
        assertThat(bust.canHit(DealerHittableStrategy)).isFalse
    }
}
