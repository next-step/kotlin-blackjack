package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.player.Hands

interface State {
    val hands: Hands

    val canContinue: Boolean
        get() = true

    val score: Int?

    fun addCard(card: Card): State
}
