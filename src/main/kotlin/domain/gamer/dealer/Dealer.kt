package domain.gamer.dealer

import domain.State
import domain.card.CardDeck
import domain.card.Cards
import domain.gamer.Gamer

class Dealer(
    override val cards: Cards = Cards(),
    initialState: State = State.Hit,
    override val name: String = "딜러",
) : Gamer {

    private var state: State = initialState
    private var onHit: (() -> Unit)? = null

    val isBust: Boolean
        get() = state == State.Bust

    override val isHit: Boolean
        get() = state == State.Hit

    override fun hit(cardDeck: CardDeck) {
        if (state != State.Hit) return
        cards.add(cardDeck.pop())
        state = newState()
        onHit?.invoke()
    }

    fun addOnHitCallback(callback: (() -> Unit)) {
        onHit = callback
    }

    private fun newState(): State {
        val result = cards.result()
        return when {
            result > Cards.BLACKJACK_POINT -> State.Bust
            result == Cards.BLACKJACK_POINT -> State.BlackJack
            result > DEALER_MAX_POINT -> State.Stay
            else -> State.Hit
        }
    }

    companion object {
        const val DEALER_MAX_POINT = 16
    }
}
