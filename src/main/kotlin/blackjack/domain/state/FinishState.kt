package blackjack.domain.state

import blackjack.domain.card.PlayingCards

abstract class FinishState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    abstract fun resultScore(): Int

    protected abstract fun earningRate(otherState: FinishState): Double

    fun profit(betAmount: Double, otherState: FinishState): Double = earningRate(otherState = otherState) * betAmount
}
