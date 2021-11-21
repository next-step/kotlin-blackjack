package blackjack.model

import blackjack.state.Ready
import blackjack.state.State
import kotlin.math.roundToInt

class Player private constructor(
    name: Name,
    val bet: Bet,
    state: State
) : Gamer(name, state) {

    override fun copy(name: Name, state: State): Gamer = Player(name, bet, state)

    fun profit(): Int = profit(bet.amount.toDouble()).roundToInt()

    companion object {
        fun ready(name: Name, bet: Bet = Bet.ZERO): Player = Player(name, bet, Ready())
    }
}
