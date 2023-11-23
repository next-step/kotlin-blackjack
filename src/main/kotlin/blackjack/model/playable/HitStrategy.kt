package blackjack.model.playable

import blackjack.model.PlayingStrategy

object HitStrategy : PlayingStrategy {
    override fun isHit(): Boolean {
        return true
    }
}
