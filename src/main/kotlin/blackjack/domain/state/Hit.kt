package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Hands

class Hit(private val hands: Hands) : State {
    override fun draw(card: Card): State {
        val hands = hands + card
        if (hands.score().overBlackjackScore()) {
            return Bust()
        }
        return Hit(hands)
    }

    override fun stay(): State = Stay()
}
