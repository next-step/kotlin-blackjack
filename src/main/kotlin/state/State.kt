package state

import Hand
import card.PlayingCard

interface State {
    val hand: Hand

    val cards: List<PlayingCard>
        get() = hand.cards

    fun drawCard(cards: PlayingCard): State

    fun stay(): State
}
