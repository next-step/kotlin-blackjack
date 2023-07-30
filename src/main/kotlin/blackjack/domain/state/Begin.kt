package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Deck

class Begin : State {
    fun drawInitialHands(card1: Card, card2: Card): State {
        val deck = Deck(listOf(card1, card2))
        if (deck.isBlackjack()) return Blackjack(deck)
        return Hit(deck)
    }
}
