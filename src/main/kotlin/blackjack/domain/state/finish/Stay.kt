package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.FinishState

class Stay(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int = playingCards.calculateAceOptimalScore()

    override fun earningRate(otherState: FinishState): Double = if (this.resultScore() > otherState.resultScore()) {
        WIN_RATE
    } else {
        LOSE_RATE
    }

    companion object {
        private const val WIN_RATE: Double = 1.0
        private const val LOSE_RATE: Double = -1.0
    }
}
