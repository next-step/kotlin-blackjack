package blackjack.model

import blackjack.state.Ready
import blackjack.state.Running
import blackjack.state.State

sealed class Gamer(val name: Name, val state: State) {

    val cards: Cards = state.cards

    val score: Int = cards.sum()

    fun draw(card: Card): Gamer = copy(state = state.draw(card))

    fun stay(): Gamer = copy(name, state.stay())

    fun isReady(): Boolean = state is Ready

    fun isRunning(): Boolean = state is Running

    fun profit(amount: Amount, state: State) = this.state.profit(amount, state)

    protected abstract fun copy(name: Name = this.name, state: State = this.state): Gamer
}

