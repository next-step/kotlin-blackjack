package blackjack.domain.state

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.strategy.hittable.HittableStrategy

sealed class Finished(
    override val cards: Cards
) : State {

    abstract fun earningRate(other: State): Double

    override fun isFinished(): Boolean = true

    override fun draw(card: Card): State = throw UnsupportedOperationException(UNSUPPORTED_DRAW_METHOD)

    override fun stay(): State = throw UnsupportedOperationException(UNSUPPORTED_STAY_METHOD)

    override fun profit(other: State, money: Money): Double {
        return money.money * earningRate(other)
    }

    override fun canHit(hittableStrategy: HittableStrategy): Boolean = false

    companion object {
        const val UNSUPPORTED_DRAW_METHOD = "Finished 상태에서는 draw()를 지원하지 않습니다."
        const val UNSUPPORTED_STAY_METHOD = "Finished 상태에서는 stay()를 지원하지 않습니다."
    }
}
