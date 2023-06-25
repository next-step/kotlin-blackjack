package next.step.blackjack.domain.player.state

import next.step.blackjack.domain.card.Cards

object BlackjackState : PlayerState() {
    override fun canHit(): Boolean = false

    override fun next(cards: Cards): PlayerState {
        throw IllegalArgumentException("카드를 더 받을 수 없습니다.")
    }
}
