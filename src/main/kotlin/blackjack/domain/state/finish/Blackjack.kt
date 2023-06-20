package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.FinishState

class Blackjack(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int = BLACKJACK_SCORE

    override fun earningRate(otherState: FinishState): Double = when (otherState) {
        is Blackjack -> TIE_RATE
        else -> WIN_RATE
    }

    companion object {
        private const val BLACKJACK_SCORE: Int = 21
        private const val WIN_RATE: Double = 1.5
        private const val TIE_RATE: Double = 0.0
    }
}
