package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.player.Hands

class Bust(override val hands: Hands) : State {
    override val canContinue = false

    override val score: Int?
        get() = null

    override fun addCard(card: Card): State {
        throw IllegalStateException("Can't add card after bust.")
    }
}
