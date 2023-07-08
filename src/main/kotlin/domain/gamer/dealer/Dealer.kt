package domain.gamer.dealer

import domain.card.CardDeck
import domain.card.Cards
import domain.gamer.Gamer

class Dealer(
    initialState: DealerState = DealerState.Hit(Cards()),
    override val name: String = "딜러"
) : Gamer {

    private var state: DealerState = initialState
    private var onHit: (() -> Unit)? = null

    override val cards: Cards
        get() = state.cards

    val isBust: Boolean
        get() = state is DealerState.Bust

    override val isHit: Boolean
        get() = state is DealerState.Hit

    override fun hit(cardDeck: CardDeck) {
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
