package blackjack.domain.state

import blackjack.domain.card.Cards

data class Bust(
    override val cards: Cards
) : Finished(cards) {

    override fun earningRate(other: State): Double {
        return when (other) {
            is Bust -> {
                DRAW_EARNING_RATE
            }
            else -> {
                LOSE_EARNING_RATE
            }
        }
    }

    companion object {
        private const val DRAW_EARNING_RATE = 0.0
        private const val LOSE_EARNING_RATE = -1.0
    }
}
