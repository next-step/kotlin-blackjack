package blackjack.model

import blackjack.state.Ready
import blackjack.state.State

class Player private constructor(
    name: Name,
    val bet: Bet,
    state: State
) : Gamer(name, state) {

    override fun copy(name: Name, state: State): Gamer = Player(name, bet, state)

    fun profit(): Profit = profit(bet.amount)

    companion object {
        fun ready(name: Name, bet: Bet = Bet.ZERO): Player = Player(name, bet, Ready())
    }
}
