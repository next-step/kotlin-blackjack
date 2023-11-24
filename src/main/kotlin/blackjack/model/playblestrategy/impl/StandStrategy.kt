package blackjack.model.playblestrategy.impl

import blackjack.model.playblestrategy.PlayingStrategy

object StandStrategy : PlayingStrategy {
    override fun isHit(): Boolean {
        return false
    }
}
