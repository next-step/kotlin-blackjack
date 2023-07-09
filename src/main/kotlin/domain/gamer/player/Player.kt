package domain.gamer.player

import domain.State
import domain.card.CardDeck
import domain.card.Cards
import domain.gamer.Gamer

open class Player(
    override val name: String,
    override val cards: Cards = Cards(),
    initialState: State = State.Hit
) : Gamer {

    private var state: State = initialState

    val isBust: Boolean
        get() = state == State.Bust

    override val isHit: Boolean
        get() = state == State.Hit

    override fun hit(cardDeck: CardDeck) {
        if (state != State.Hit) return
        cards.add(cardDeck.pop())
        state = newState()
    }

    fun stay() {
        state = State.Stay
    }

    private fun newState(): State {
        val result = cards.result()
        return when {
            result == Cards.BLACKJACK_POINT -> State.BlackJack
            result < Cards.BLACKJACK_POINT -> State.Hit
            else -> State.Bust
        }
    }
}
