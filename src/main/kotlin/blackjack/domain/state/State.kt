package blackjack.domain.state

import blackjack.domain.card.Card

interface State {
    fun draw(card: Card): State
    fun stay(): State
}
