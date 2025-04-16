package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.player.Hands

interface State {
    val hands: Hands

    fun addCard(card: Card): State
}
