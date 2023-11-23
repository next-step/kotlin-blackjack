package blackjack.model.playable

import blackjack.model.PlayingStrategy

object StandStrategy : PlayingStrategy {
    override fun isHit(): Boolean {
        return false
    }
}
