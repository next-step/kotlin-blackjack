package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Hands

class Hit(override val hands: Hands) : State {
    override fun draw(card: Card): State {
        val hands = hands + card
        if (hands.score().overBlackjackScore()) {
            return Bust(hands = hands)
        }
        return Hit(hands)
    }

    override fun stay(): State = Stay(hands = hands)
}
