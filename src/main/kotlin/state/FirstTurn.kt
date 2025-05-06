package state

import Hand
import card.PlayingCard

class FirstTurn(override val hand: Hand) : State {
    override fun drawCards(cards: List<PlayingCard>): State {
        hand.add(cards)

        if (hand.isBlackjack()) return Blackjack(hand)
        return Hit(hand)
    }
}
