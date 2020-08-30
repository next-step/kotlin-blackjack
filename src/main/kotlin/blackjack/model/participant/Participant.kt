package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Cards
import blackjack.model.state.State

interface Participant {
    val state: State

    fun takeDefaultCards(takeCards: () -> Cards): Participant

    fun draw(newCard: Card): Participant

    fun canDraw(): Boolean

    fun hasTwoCards(): Boolean = state.cards.isSizeOfTwo()

    fun score(): Int = state.cards.score()

    fun cardsDetail(): String = state.cards.toString()
}
