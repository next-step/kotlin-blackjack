package domain.state

import domain.card.Card
import domain.card.Cards

interface State {

    fun draw(card: Card): State
    fun stop(): State
    fun getCards(): Cards
}
