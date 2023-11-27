package blackjack.model.player.playblestrategy.impl

import blackjack.model.player.playblestrategy.PlayingStrategy

object StandStrategy : PlayingStrategy {
    override fun isHit(): Boolean {
        return false
    }
}
