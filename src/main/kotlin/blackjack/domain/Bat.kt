package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.State

class Bat(private val money: Double) {
    fun profit(state: State, dealer: Dealer): Double {
        return when {
            dealer.state is Blackjack && state is Blackjack -> money * 0.0
            dealer.state is Bust -> money
            state is Bust -> money * state.earningRate()
            state.score() >= dealer.score() -> money * state.earningRate()
            else -> money * -state.earningRate()
        }
    }
}
