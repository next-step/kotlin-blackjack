package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.player.Hands

class InitialState(override val hands: Hands = Hands()) : State {
    override fun addCard(card: Card): State {
        val hands = hands + card
        if (!hands.initialized) {
            return InitialState(hands)
        }

        return Hit(hands)
    }
}
