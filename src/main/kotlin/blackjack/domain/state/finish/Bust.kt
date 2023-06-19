package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.FinishState

class Bust(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int = BUST_SCORE
    override fun earningRate(otherState: FinishState): Double = BUST_EARNING_RATE

    companion object {
        private const val BUST_SCORE: Int = 0
        private const val BUST_EARNING_RATE: Double = -1.0
    }
}
