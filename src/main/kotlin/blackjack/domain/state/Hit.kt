package blackjack.domain.state

import blackjack.domain.Hands
import blackjack.domain.card.Card

class Hit(override val hands: Hands) : State {
    override fun addCard(card: Card): State {
        val hands = hands + card
        if (hands.isBust()) {
            Bust(hands)
        }

        return Hit(hands)
    }
}
