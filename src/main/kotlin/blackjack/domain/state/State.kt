package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

interface State {
    val hand: Hand
    fun draw(card: Card): State
}
