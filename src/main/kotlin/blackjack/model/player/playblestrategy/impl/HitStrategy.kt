package blackjack.model.player.playblestrategy.impl

import blackjack.model.player.playblestrategy.PlayingStrategy

object HitStrategy : PlayingStrategy {
    override fun isHit(): Boolean {
        return true
    }
}
