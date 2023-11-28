package blackjack.dealer

import action.BlackJackAction
import blackjack.deck.Deck
import blackjack.hand.Hand

interface DealerStrategy {
    fun decideAction(hand: Hand, deck: Deck): BlackJackAction
}
