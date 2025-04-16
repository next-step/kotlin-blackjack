package blackjack.domain.state

import blackjack.domain.Hands
import blackjack.domain.card.Card

interface State {
    val hands: Hands

    fun addCard(card: Card): State
}
