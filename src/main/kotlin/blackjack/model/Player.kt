package blackjack.model

import blackjack.state.Ready
import blackjack.state.State

class Player private constructor(
    name: Name,
    val bet: Bet,
    state: State
) : Gamer(name, state) {

    override fun copy(name: Name, state: State): Gamer = Player(name, bet, state)

    fun result(dealerScore: Int): Result = when {
        score > TWENTY_ONE -> Result.LOSE
        dealerScore > TWENTY_ONE -> Result.WIN
        score > dealerScore -> Result.WIN
        score < dealerScore -> Result.LOSE
        else -> Result.PUSH
    }

    companion object {
        private const val TWENTY_ONE = 21

        fun ready(name: Name, bet: Bet = Bet.ZERO): Player = Player(name, bet, Ready())
    }
}
