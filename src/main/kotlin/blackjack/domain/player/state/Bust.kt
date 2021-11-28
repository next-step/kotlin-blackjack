package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Bust(override val hands: Hands) : Finish(hands) {

    override fun earningsRate(otherState: State): Double =
        when (otherState as Finish) {
            is Bust -> BUST_RATE
            is Stay -> STAY_RATE
            is BlackJack -> BLACKJACK_RATE
        }

    companion object {
        private const val BUST_RATE = 1.5
        private const val STAY_RATE = 1.5
        private const val BLACKJACK_RATE = 1.0
    }
}
