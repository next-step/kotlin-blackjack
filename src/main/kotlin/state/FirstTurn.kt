package state

import Hand
import PlayingCard

class FirstTurn(private val hand: Hand) : State {
    fun drawCards(cards: List<PlayingCard>): State {
        hand.add(cards)

        if (hand.isBlackjack()) return Blackjack()
        return Hit()
    }
}
