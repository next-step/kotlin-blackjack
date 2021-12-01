package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Bust(override val hands: Hands) : Finish(hands) {

    override fun earningsRate(otherState: State): Double {
        return when (otherState as Finish) {
            is BlackJack -> LOSE_RATE
            is Bust -> DRAW_RATE
            is Stay -> LOSE_RATE
        }
    }

    companion object {
        private const val DRAW_RATE = 0.0
        private const val LOSE_RATE = -1.0
    }
}
