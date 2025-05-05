package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.state.InitialState
import blackjack.domain.state.State

class Player(
    val name: Name,
    _state: State = InitialState(),
) {
    var state = _state
        private set

    val cards: List<Card>
        get() = state.hands.cards

    val canDraw: Boolean
        get() = state.canContinue

    val score: Int?
        get() = state.score

    constructor(rawName: String) : this(Name(rawName))
    constructor(rawName: String, state: State) : this(Name(rawName), state)

    fun draw(card: Card) {
        state = state.addCard(card)
    }
}
