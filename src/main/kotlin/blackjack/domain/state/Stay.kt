package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.util.exhaustive

class Stay(
    override val cards: Cards
) : Finished(cards) {

    override fun earningRate(other: State): Double {
        return when (other as Finished) {
            is Bust -> WIN_EARNING_RATE
            is Blackjack -> LOSE_EARNING_RATE
            is Stay -> {
                compareToStay(other)
            }
        }.exhaustive
    }

    private fun compareToStay(other: Finished): Double {
        return when {
            cards.getScore() > other.score -> WIN_EARNING_RATE
            cards.getScore() == other.score -> DRAW_EARNING_RATE
            else -> LOSE_EARNING_RATE
        }
    }

    companion object {
        private const val WIN_EARNING_RATE = 1.0
        private const val DRAW_EARNING_RATE = 0.0
        private const val LOSE_EARNING_RATE = -1.0
    }
}
