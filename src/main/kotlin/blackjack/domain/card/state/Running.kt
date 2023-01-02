package blackjack.domain.card.state

import blackjack.domain.card.PlayingCards

abstract class Running(override val cards: PlayingCards) : State() {
    override fun isFinished(): Boolean {
        return false
    }

    override fun earningRate(money: Int): Double {
        throw IllegalStateException("아직 게임이 끝나지 않았습니다.")
    }
}
