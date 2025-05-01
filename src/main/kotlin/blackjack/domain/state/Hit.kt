package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.player.Hands

class Hit(override val hands: Hands) : State {
    override fun addCard(card: Card): State {
        val hands = hands + card
        if (hands.isBust()) {
            return Bust(hands)
        }

        return Hit(hands)
    }
}
