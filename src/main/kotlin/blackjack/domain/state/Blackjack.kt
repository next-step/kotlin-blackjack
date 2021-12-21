package blackjack.domain.state

import blackjack.domain.card.Cards

data class Blackjack(override val cards: Cards) : Finished(cards) {

    override fun earningRate(other: State): Double {
        return when (other) {
            is Blackjack -> DRAW_EARNING_RATE
            else -> WIN_EARNING_RATE
        }
    }

    companion object {
        private const val WIN_EARNING_RATE = 1.5
        private const val DRAW_EARNING_RATE = 0.0
    }
}
