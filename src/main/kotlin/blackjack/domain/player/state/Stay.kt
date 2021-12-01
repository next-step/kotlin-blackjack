package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Stay(override val hands: Hands) : Finish(hands) {

    override fun earningsRate(otherState: State): Double {
        return when (otherState as Finish) {
            is BlackJack -> LOSE_RATE
            is Bust -> WIN_RATE
            is Stay -> calculateRate(otherState)
        }
    }

    private fun calculateRate(otherState: Finish): Double {
        return when {
            hands.score() > otherState.score() -> WIN_RATE
            hands.score() == otherState.score() -> DRAW_RATE
            else -> LOSE_RATE
        }
    }

    companion object {
        private const val WIN_RATE = 1.0
        private const val DRAW_RATE = 0.0
        private const val LOSE_RATE = -1.0
    }
}
