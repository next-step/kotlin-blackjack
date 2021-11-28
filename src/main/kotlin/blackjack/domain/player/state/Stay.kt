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
        val compareValue = hands.score().compareTo(otherState.score())
        return when {
            compareValue > COMPARE_CONDITION -> WIN_RATE
            compareValue == COMPARE_CONDITION -> DRAW_RATE
            else -> LOSE_RATE
        }
    }

    companion object {
        private const val COMPARE_CONDITION = 0
        private const val WIN_RATE = 1.0
        private const val DRAW_RATE = 0.0
        private const val LOSE_RATE = -1.0
    }
}
