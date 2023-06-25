package next.step.blackjack.domain.player.state

import next.step.blackjack.domain.player.PlayerCards

object BurstState : PlayerState() {
    override fun canHit(): Boolean = false

    override fun next(cards: PlayerCards): PlayerState {
        throw IllegalArgumentException("카드를 더 받을 수 없습니다.")
    }
}
