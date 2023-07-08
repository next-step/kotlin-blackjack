package domain.gamer.player

import domain.card.CardDeck
import domain.card.Cards
import domain.gamer.Gamer

open class Player(override val name: String, initialState: PlayerState = PlayerState.Hit(Cards())) : Gamer {

    private var state: PlayerState = initialState

    override val cards: Cards
        get() = state.cards

    val isBust: Boolean
        get() = state is PlayerState.Bust

    override val isHit: Boolean
        get() = state is PlayerState.Hit

    override fun hit(cardDeck: CardDeck) {
        val capturedState = state
        if (capturedState is PlayerState.Hit) {
            state = capturedState.hit(cardDeck.pop())
        }
    }

    fun stay() {
        state = PlayerState.Stay(state.cards)
    }
}
