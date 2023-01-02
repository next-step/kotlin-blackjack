package blackjack.domain.state

import blackjack.domain.Hand

abstract class Running(override val hand: Hand) : Started(hand) {
    override fun isFinished() = false
    override fun profit(money: Double): Double {
        throw IllegalStateException("진행중인 상태에서는 수익을 계산할 수 없습니다")
    }

    override fun stay() = Stay(hand)
}
