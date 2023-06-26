package domain.player

import domain.card.Card
import domain.card.Cards
import domain.state.State
import domain.state.TerminationState
open class Player(val name: String, state: State) {

    var state = state
        private set

    val cards: Cards
        get() = state.getCards()

    open fun draw(card: Card): State {
        this.state = state.draw(card)
        return this.state
    }

    fun stop(): State {
        this.state = state.stop()
        return this.state
    }

    fun getPlayerGameResult(dealer: Dealer): PlayerGameResult = state.getPlayerGameResult(dealer.state)

    fun getRevenue(dealer: Dealer): Int = this.state.getRevenue(dealer.state)

    fun isTerminated(): Boolean = state is TerminationState
}
