package state

import Hand
import card.PlayingCard

interface State {
    val hand: Hand

    fun drawCards(cards: List<PlayingCard>): State
}
