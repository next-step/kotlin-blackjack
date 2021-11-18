package domain.player.state

import domain.card.PlayingCards
import exception.IllegalEarningRate
import exception.IllegalScoreException

sealed class Running(cards: PlayingCards) : PlayerState(cards) {
    fun cardState() = cards.state()
    override fun isFinished() = false

    override fun earningRate(): Double {
        throw IllegalEarningRate()
    }

    override fun score(): Int {
        throw IllegalScoreException()
    }

    abstract fun nextState(): PlayerState
}
