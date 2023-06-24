package domain.state

import domain.card.Card
import domain.card.Cards

open class TerminationState(private val cards: Cards) : State {
    override fun draw(card: Card): State = this

    override fun stop(): State = this

    override fun getCards(): Cards = this.cards
}
