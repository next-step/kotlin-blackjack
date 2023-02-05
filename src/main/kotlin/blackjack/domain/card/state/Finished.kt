package blackjack.domain.card.state

import blackjack.domain.bet.Money
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

abstract class Finished(override val cards: PlayingCards) : State() {
    override val isFinished: Boolean = true

    override fun draw(playingCard: PlayingCard): State {
        throw IllegalStateException("이미 끝난 상태입니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("이미 끝난 상태입니다.")
    }

    override fun earningRate(money: Money): Double {
        return money.toDouble() * rate
    }
}
