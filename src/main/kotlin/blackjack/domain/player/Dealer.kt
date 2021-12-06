package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

class Dealer(name: PlayerName = PlayerName(DEAFAULT_DEALER_NAME), hand: Hand = Hand()) : Player(name, hand) {
    override fun isHandAddable() = getMaxHandValue() <= DEALER_MIN_HAND_VALUE

    override fun addCardToHand(card: Card): Boolean {
        if (isHandAddable())
            return hand.addCardToHand(card)
        return false
    }

    companion object {
        const val DEAFAULT_DEALER_NAME = "딜러"
        const val DEALER_MIN_HAND_VALUE = 16
    }
}
