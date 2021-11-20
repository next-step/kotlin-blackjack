package domain.player.state

import domain.card.PlayingCard
import domain.card.PlayingCards

sealed class PlayerState(val cards: PlayingCards) {
    abstract fun isFinished(): Boolean
    abstract fun stay(): PlayerState
    abstract fun draw(playingCard: PlayingCard): PlayerState
    abstract fun earningRate(win: Boolean): Double
    abstract fun score(): Int
}
