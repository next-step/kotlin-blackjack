package blackjack.state

import blackjack.domain.Money
import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Hit
import blackjack.domain.state.Initial
import blackjack.domain.state.Running.Companion.UNSUPPORTED_PROFIT_METHOD
import blackjack.domain.state.State
import blackjack.domain.state.Stay
import blackjack.domain.strategy.hittable.DealerHittableStrategy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitialTest {

    @Test
    fun `Initial상태에서 뽑은 2장의 카드가 21점이면 Blackjack이다`() {
        var state: State = Initial()
        state = state
            .draw(CARD_HEART_ACE)
            .draw(CARD_HEART_KING)

        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `Initial상태에서 뽑은 2장의 카드가 21점 미만이면 Hit이다`() {
        var state: State = Initial()
        state = state
            .draw(CARD_HEART_ACE)
            .draw(CARD_HEART_TWO)

        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Initial상태에서 1장의 카드를 뽑으면 Initial 상태이다`() {
        var state: State = Initial()
        state = state
            .draw(CARD_HEART_ACE)

        assertThat(state).isInstanceOf(Initial::class.java)
    }

    @Test
    fun `Initial은 Finished 상태가 아니다`() {
        val state: State = Initial()
        val isFinished: Boolean = state.isFinished()

        assertThat(isFinished).isFalse
    }

    @Test
    fun `Initial은 profit()을 계산할 수 없다`() {
        val state: State = Initial()
            .draw(CARD_HEART_ACE)
            .draw(CARD_HEART_TWO)

        val stay = Stay(Cards(listOf(CARD_HEART_TWO, CARD_HEART_FOUR)))

        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                state.profit(stay, Money.from("3000"))
            }
            .withMessage(UNSUPPORTED_PROFIT_METHOD)
    }

    @Test
    fun `Initial를 통해 score를 가져올 수 있다`() {
        val state: State = Initial()
            .draw(CARD_HEART_ACE)

        assertThat(state.score).isEqualTo(Score(11))
    }

    @Test
    fun `Initial의 canHit()은 True이다`() {
        val initial: State = Initial()
            .draw(CARD_HEART_ACE)

        assertThat(initial.canHit(DealerHittableStrategy)).isTrue
    }
}
