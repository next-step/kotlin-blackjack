package next.step.blackjack.domain.player.state

import next.step.blackjack.domain.card.Cards

sealed class PlayerState {

    abstract fun canHit(): Boolean
    
    abstract fun next(cards: Cards): PlayerState
}
