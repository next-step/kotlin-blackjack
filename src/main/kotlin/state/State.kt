package state

import Hand
import PlayingCard

interface State {
    val hand: Hand

    fun drawCards(cards: List<PlayingCard>): State
}
