package domain.dealer

import domain.card.CardDeck
import domain.card.Cards

class Dealer(initialState: DealerState = DealerState.Hit(Cards())) {

    private var state: DealerState = initialState
    private var onHit: (() -> Unit)? = null

    val cards: Cards
        get() = state.cards

    val canReceiveMoreCard: Boolean
        get() = state.canReceiveMoreCard

    val isBlackJack: Boolean
        get() = state is DealerState.BlackJack

    val isBust: Boolean
        get() = state is DealerState.Bust

    val result: Int
        get() = state.cards.result()

    fun hit(cardDeck: CardDeck) {
        val capturedState = state
        if (capturedState is DealerState.Hit) {
            state = capturedState.hit(cardDeck.pop())
            onHit?.invoke()
        }
    }

    fun addOnHitCallback(callback: (() -> Unit)) {
        onHit = callback
    }

    companion object {
        const val DEALER_MAX_POINT = 16
    }
}
