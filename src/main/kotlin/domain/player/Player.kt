package domain.player

import domain.card.BlackjackCard
import domain.card.BlackjackCards
import domain.state.StartState
import domain.state.State

class Player(name: String, card1: BlackjackCard, card2: BlackjackCard) {

    val name: String
    var state: State
        private set
    val cards: BlackjackCards
        get() = state.getCards()

    init {
        this.name = name
        this.state = StartState.start(card1, card2)
    }

    fun draw(card: BlackjackCard): State = state.draw(card)

    fun stop(): State = state.stop()
}
