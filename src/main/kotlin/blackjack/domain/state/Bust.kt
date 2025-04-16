package blackjack.domain.state

import blackjack.domain.Hands
import blackjack.domain.card.Card

class Bust(override val hands: Hands) : State {
    override fun addCard(card: Card): State {
        throw IllegalStateException("Can't add card after bust.")
    }
}
