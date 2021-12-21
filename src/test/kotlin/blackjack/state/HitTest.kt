package blackjack.state

import blackjack.domain.Money
import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Running.Companion.UNSUPPORTED_PROFIT_METHOD
import blackjack.domain.state.Stay
import blackjack.domain.strategy.hittable.DealerHittableStrategy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HitTest {

    private lateinit var hit: Hit

    @BeforeEach
    internal fun setUp() {
        hit = Hit(Cards(listOf(CARD_HEART_TWO, CARD_HEART_KING)))
    }

    @Test
    fun `Hit상태에서 3장의 카드의 점수가 21 미만이면 state는 Hit이다`() {
        val state = hit.draw(CARD_HEART_ACE)

        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit상태에서 3장의 카드의 점수가 21이 넘으면 state는 Bust이다`() {
        val state = hit.draw(CARD_HEART_TEN)

        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit상태는 Finished상태가 아니다`() {
        val isFinished: Boolean = hit.isFinished()

        assertThat(isFinished).isFalse
    }

    @Test
    fun `Hit상태에서 stay할 수 있다`() {
        val state = hit.stay()

        assertThat(state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `Hit상태에서는 profit()을 계산할 할 수 없다`() {
        val stay = Stay(Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO)))

        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                hit.profit(stay, Money.from("3000"))
            }
            .withMessage(UNSUPPORTED_PROFIT_METHOD)
    }

    @Test
    fun `Hit을 통해 score를 가져올 수 있다`() {
        assertThat(hit.score).isEqualTo(Score(12))
    }

    @Test
    fun `Hit의 canHit()은 True이다`() {
        assertThat(hit.canHit(DealerHittableStrategy)).isTrue
    }
}
