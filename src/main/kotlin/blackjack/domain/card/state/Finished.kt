package blackjack.domain.card.state

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Finished(
    override val cards: Cards
) : State {

    abstract val earningRate: Double

    override fun draw(card: Card): State {
        throw IllegalStateException("더이상 카드를 받을 수 없는 상태.")
    }

    override fun stay(): State {
        throw IllegalStateException("stay가 될 수 없는 상태")
    }

    override fun isFinished() = true

    override fun profit(money: Money): Money {
        return money * earningRate
    }
}
