package domain.dealer

import domain.card.Card
import domain.card.Cards

class Dealer(initialState: DealerState = DealerState.Hit(Cards())) {

    private var state: DealerState = initialState

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

    fun hit(card: Card) {
        val capturedState = state
        if (capturedState is DealerState.Hit) {
            state = capturedState.hit(card)
        }
    }

    companion object {
        const val DEALER_MAX_POINT = 16
    }
}
