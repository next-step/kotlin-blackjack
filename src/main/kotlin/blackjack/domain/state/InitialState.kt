package blackjack.domain.state

import blackjack.domain.Hands
import blackjack.domain.card.Card

class InitialState(override val hands: Hands = Hands()) : State {
    override fun addCard(card: Card): State {
        val hands = hands + card
        if (!hands.initialized) {
            return InitialState(hands)
        }

        return Hit(hands)
    }
}
