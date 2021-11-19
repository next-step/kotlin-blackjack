package domain.player.state

import domain.card.CardState
import domain.card.PlayingCards
import exception.IllegalEarningRate

sealed class Running(cards: PlayingCards) : PlayerState(cards) {
    fun cardState(): CardState = cards.state()
    override fun isFinished(): Boolean = false

    override fun earningRate(win: Boolean): Double {
        throw IllegalEarningRate()
    }

    override fun score(): Int = cards.score()

    abstract fun nextState(): PlayerState
}
