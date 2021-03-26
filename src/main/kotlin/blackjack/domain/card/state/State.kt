package blackjack.domain.card.state

import blackjack.domain.card.Card

interface State {

    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean
}
