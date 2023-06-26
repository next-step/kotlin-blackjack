package domain.player

import domain.card.Card
import domain.card.Cards
import domain.state.Burst
import domain.state.StartState
import domain.state.State
import domain.state.TerminationState
open class Player(name: String, cards: Cards) {

    val name: String
    var state: State
        private set
    val cards: Cards
        get() = state.getCards()

    init {
        this.name = name
        this.state = StartState.start(cards)
    }

    open fun draw(card: Card): State {
        this.state = state.draw(card)
        return this.state
    }

    fun stop(): State {
        this.state = state.stop()
        return this.state
    }

    fun getPlayerGameResult(dealer: Dealer): PlayerGameResult = when {
        this.state is Burst -> PlayerGameResult.LOSE
        dealer.state is Burst -> PlayerGameResult.WIN
        this.cards.sum > dealer.cards.sum -> PlayerGameResult.WIN
        this.cards.sum == dealer.cards.sum -> PlayerGameResult.DRAW
        else -> PlayerGameResult.LOSE
    }

    fun isTerminated(): Boolean = state is TerminationState
}
