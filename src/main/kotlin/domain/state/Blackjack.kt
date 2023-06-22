package domain.state

import domain.card.BlackjackCard
import domain.card.BlackjackCards

class Blackjack(cards: BlackjackCards) : TerminationState(cards) {
    companion object {
        fun isBlackjack(card1: BlackjackCard, card2: BlackjackCard): Boolean =
            (card1.number.isAce() && card2.number.isTenScore()) ||
                (card2.number.isAce() && card1.number.isTenScore())
    }
}
