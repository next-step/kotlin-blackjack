package domain.gamer.player

import domain.Score
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
        val result = cards.score()
        return when {
            result == Score.BLACKJACK -> State.BlackJack
            result < Score.BLACKJACK -> State.Hit
            else -> State.Bust
        }
    }
}
