package blackjack.domain.card.state

import blackjack.domain.bet.Money
import blackjack.domain.card.PlayingCards

abstract class Running(override val cards: PlayingCards) : State() {
    override val isFinished: Boolean = false

    override fun earningRate(money: Money): Double {
        throw IllegalStateException("아직 게임이 끝나지 않았습니다.")
    }
}
