package domain.player

import domain.card.Card
import domain.card.Cards
import domain.state.StartState
import domain.state.State
import domain.state.TerminationState
import java.math.BigDecimal
import java.math.RoundingMode

open class Player(val name: String, val betAmount: BetAmount) {

    lateinit var state: State
        private set

    val cards: Cards
        get() = state.getCards()

    fun initGame(cards: Cards) {
        state = StartState.start(cards)
    }

    open fun draw(card: Card): State {
        this.state = state.draw(card)
        return this.state
    }

    fun stop(): State {
        this.state = state.stop()
        return this.state
    }

    fun getPlayerGameResult(dealer: Dealer): PlayerGameResult = state.getPlayerGameResult(dealer.state)

    fun getRevenue(dealer: Dealer): Int {
        val revenueRate = state.getRevenueRate(dealer.state)
        return BigDecimal(betAmount.amount).multiply(revenueRate).setScale(0, RoundingMode.DOWN).toInt()
    }

    fun isTerminated(): Boolean = state is TerminationState
}
