package blackjack.domain.state

import blackjack.domain.Money
import blackjack.domain.card.Cards
import blackjack.domain.strategy.hittable.HittableStrategy

abstract class Running(
    override val cards: Cards
) : State {

    override fun profit(other: State, money: Money): Double =
        throw UnsupportedOperationException(UNSUPPORTED_PROFIT_METHOD)

    override fun isFinished(): Boolean = false

    override fun stay(): State = Stay(cards)

    override fun canHit(hittableStrategy: HittableStrategy): Boolean = cards
        .getScore()
        .canHit(hittableStrategy)

    companion object {
        const val UNSUPPORTED_PROFIT_METHOD = "Running 상태에서는 profit()를 지원하지 않습니다."
    }
}
