package blackjack.model.playblestrategy.impl

import blackjack.model.playblestrategy.PlayingStrategy

object HitStrategy : PlayingStrategy {
    override fun isHit(): Boolean {
        return true
    }
}
