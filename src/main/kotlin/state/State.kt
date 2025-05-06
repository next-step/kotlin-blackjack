package state

import Hand
import card.PlayingCard

interface State {
    val hand: Hand

    fun drawCard(cards: PlayingCard): State
}
