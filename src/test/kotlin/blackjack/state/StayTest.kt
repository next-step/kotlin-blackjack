package blackjack.state

import blackjack.domain.Money
import blackjack.domain.Score
import blackjack.domain.card.Cards
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_DRAW_METHOD
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_STAY_METHOD
import blackjack.domain.state.Stay
import blackjack.domain.strategy.hittable.DealerHittableStrategy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StayTest {

    private lateinit var stay: Stay

    @BeforeEach
    internal fun setUp() {
        stay = Stay(Cards(listOf(CARD_HEART_KING, CARD_HEART_FOUR)))
    }

    @Test
    fun `Stay상태와 Bust와의 매칭시 배팅금액의 1배가 된다`() {
        val bust = Bust(Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO, CARD_HEART_TEN)))

        val match = stay.profit(bust, Money.from("3000"))

        assertThat(match).isEqualTo(3000.0)
    }

    @Test
    fun `Stay상태와 Blackjack와의 매칭시 배팅금액의 -1배가 된다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_ACE, CARD_HEART_KING)))

        val match = stay.profit(blackjack, Money.from("3000"))

        assertThat(match).isEqualTo(-3000.0)
    }

    @Test
    fun `Stay상태와 Stay상태가 매칭시 배팅금액의 1배가 된다`() {
        val stayState = Stay(Cards(listOf(CARD_HEART_TWO, CARD_HEART_FOUR)))

        val match = stay.profit(stayState, Money.from("3000"))

        assertThat(match).isEqualTo(3000.0)
    }

    @Test
    fun `Stay상태와 Stay상태가 매칭시 점수가 같으면 배팅금액의 0배가 된다`() {
        val stayState = Stay(Cards(listOf(CARD_HEART_TEN, CARD_HEART_FOUR)))

        val match = stay.profit(stayState, Money.from("3000"))

        assertThat(match).isEqualTo(0.0)
    }

    @Test
    fun `Stay상태와 Stay상태가 매칭시 점수가 낮으면 배팅금액의 -1배가 된다`() {
        val stayState = Stay(Cards(listOf(CARD_HEART_KING, CARD_HEART_TEN)))

        val match = stay.profit(stayState, Money.from("3000"))

        assertThat(match).isEqualTo(-3000.0)
    }

    @Test
    fun `Stay상태와 Blackjack상태와의 매칭시 배당률은 -1이다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_ACE, CARD_HEART_KING)))

        val earningRate = stay.earningRate(blackjack)

        assertThat(earningRate).isEqualTo(-1.0)
    }

    @Test
    fun `Stay상태와 Bust상태가 매칭시 배당률은 1이다`() {
        val bust = Bust(Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO, CARD_HEART_TEN)))

        val earningRate = stay.earningRate(bust)

        assertThat(earningRate).isEqualTo(1.0)
    }

    @Test
    fun `Stay상태와 Stay상태가 매칭시 이길 경우 배당률은 1이다`() {
        val stayState = Stay(Cards(listOf(CARD_HEART_TWO, CARD_HEART_TWO)))

        val earningRate = stay.earningRate(stayState)

        assertThat(earningRate).isEqualTo(1.0)
    }

    @Test
    fun `Stay상태와 Stay상태가 매칭시 비길 경우 배당률은 0이다`() {
        val stayState = Stay(Cards(listOf(CARD_HEART_KING, CARD_HEART_FOUR)))

        val earningRate = stay.earningRate(stayState)

        assertThat(earningRate).isEqualTo(0.0)
    }

    @Test
    fun `Stay상태와 Stay상태가 매칭시 배당률은 질 경우 -1이다`() {
        val stayState = Stay(Cards(listOf(CARD_HEART_KING, CARD_HEART_TEN)))

        val earningRate = stay.earningRate(stayState)

        assertThat(earningRate).isEqualTo(-1.0)
    }

    @Test
    fun `Stay상태는 Finished 상태이다`() {
        val isFinished = stay.isFinished()

        assertThat(isFinished).isTrue
    }

    @Test
    fun `Stay상태에서는 draw()할 수 없다`() {
        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy {
            stay.draw(CARD_HEART_ACE)
        }.withMessage(UNSUPPORTED_DRAW_METHOD)
    }

    @Test
    fun `Stay상태에서는 stay()할 수 없다`() {
        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy { stay.stay() }
            .withMessage(UNSUPPORTED_STAY_METHOD)
    }

    @Test
    fun `Stay상태에서 score를 알 수 있다`() {
        assertThat(stay.score).isEqualTo(Score(14))
    }

    @Test
    fun `Initial의 canHit()은 False이다`() {
        assertThat(stay.canHit(DealerHittableStrategy)).isFalse
    }
}
