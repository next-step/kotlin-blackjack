package domain.player

import domain.card.card
import domain.card.Cards
import domain.state.StartState
import domain.state.State

class Player(name: String, card1: card, card2: card) {

    val name: String
    var state: State
        private set
    val cards: Cards
        get() = state.getCards()

    init {
        this.name = name
        this.state = StartState.start(card1, card2)
    }

    fun draw(card: card): State {
        this.state = state.draw(card)
        return this.state
    }

    fun stop(): State {
        this.state = state.stop()
        return this.state
    }
}
