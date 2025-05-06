package state

import Hand
import card.PlayingCard

class Hit(override val hand: Hand) : State {
    override fun drawCards(cards: List<PlayingCard>): State {
        hand.add(cards)

        return if (hand.isBust()) {
            return Bust(hand)
        } else {
            Hit(hand)
        }
    }

    fun stay(): State {
        return Stay(hand)
    }
}
