package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Hands

class First(private val hands: Hands) : State {
    constructor(cards: List<Card> = emptyList()) : this(Hands(cards))

    override fun draw(card: Card): State {
        val hands = hands + card
        if (hands.score().isBlackjackScore()) {
            return Blackjack()
        }

        if (hands.hasTwo()) {
            return Hit(hands)
        }

        return First(hands)
    }

    override fun stay(): State = throw IllegalStateException()
}
