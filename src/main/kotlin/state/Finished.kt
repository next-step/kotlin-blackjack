package state

import Hand
import card.PlayingCard

abstract class Finished(override val hand: Hand) : State {
    override fun drawCard(card: PlayingCard): State {
        throw IllegalStateException()
    }

    override fun stay(): State {
        return this
    }
}
