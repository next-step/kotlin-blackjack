package state

import Hand
import PlayingCard

abstract class Finished(override val hand: Hand) : State {
    override fun drawCards(cards: List<PlayingCard>): State {
        throw IllegalStateException()
    }
}
