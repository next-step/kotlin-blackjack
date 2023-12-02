package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Hands

class First(override val hands: Hands) : State {
    constructor(cards: Cards) : this(Hands(cards))

    override fun draw(card: Card): State {
        val hands = hands + card
        if (hands.score().isBlackjackScore()) {
            return Blackjack(hands = hands)
        }

        if (hands.hasTwo()) {
            return Hit(hands)
        }

        return First(hands)
    }

    override fun stay(): State = throw IllegalStateException()
}
