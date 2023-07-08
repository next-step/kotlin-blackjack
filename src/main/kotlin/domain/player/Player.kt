package domain.player

import domain.card.Card
import domain.card.Cards

open class Player(val name: String, initialState: PlayerState = PlayerState.Hit(Cards())) {

    private var state: PlayerState = initialState

    val cards: Cards
        get() = state.cards

    val result: Int
        get() = state.cards.result()

    val isBust: Boolean
        get() = state is PlayerState.Bust

    val isHit: Boolean
        get() = state is PlayerState.Hit

    open val canReceiveMoreCard: Boolean
        get() = state.canReceiveMoreCard

    fun hit(card: Card) {
        val capturedState = state
        if (capturedState is PlayerState.Hit) {
            state = capturedState.hit(card)
        }
    }

    fun stay() {
        state = PlayerState.Stay(state.cards)
    }
}
