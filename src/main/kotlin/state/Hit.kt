package state

import Hand
import card.PlayingCard

class Hit(override val hand: Hand) : State {
    override fun drawCard(card: PlayingCard): State {
        hand.add(card)

        return if (hand.isBust()) {
            return Bust(hand)
        } else {
            Hit(hand)
        }
    }

    override fun stay(): State {
        return Stay(hand)
    }
}
