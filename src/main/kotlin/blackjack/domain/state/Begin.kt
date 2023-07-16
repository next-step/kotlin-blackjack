package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Deck

class Begin {
    fun drawInitialHands(card1: Card, card2: Card): State {
        val deck = Deck(listOf(card1, card2))
        if (deck.isBlackjack()) return Blackjack()
        return Hit()
    }
}
