package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class BlackJack(override val hands: Hands) : Finish(hands) {

    override fun earningsRate(otherState: State): Double {
        return when (otherState as Finish) {
            is BlackJack -> DRAW_RATE
            is Bust -> WIN_RATE
            is Stay -> WIN_RATE
        }
    }

    companion object {
        private const val WIN_RATE = 1.5
        private const val DRAW_RATE = 0.0
    }
}
