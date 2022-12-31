package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Hands

interface State {
    fun draw(cards: Set<Card>): State

    val hands: Hands
}
