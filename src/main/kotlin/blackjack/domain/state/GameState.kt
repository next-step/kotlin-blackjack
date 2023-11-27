package blackjack.domain.state

import blackjack.domain.Card

interface State {
    fun draw(card: Card): State
}



