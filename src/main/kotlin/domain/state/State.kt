package domain.state

import domain.card.card
import domain.card.Cards

interface State {

    fun draw(card: card): State
    fun stop(): State
    fun getCards(): Cards
}
