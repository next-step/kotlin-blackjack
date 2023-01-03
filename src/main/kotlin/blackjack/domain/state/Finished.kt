package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

abstract class Finished(override val hand: Hand) : Started(hand) {
    override fun draw(card: Card): State {
        throw IllegalStateException("카드를 더 받을 수 없습니다.")
    }

    override fun isFinished() = true
    override fun stay(): State {
        throw IllegalStateException("이미 완료된 상태에서 완료 상태로 변경할 수 없습니다.")
    }
}
