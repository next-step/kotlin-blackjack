package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult

open class TerminationState(private val cards: Cards, private val betAmount: Int = 0) : State {
    override fun draw(card: Card): State = this

    override fun stop(): State = this

    override fun getCards(): Cards = this.cards

    override fun getPlayerGameResult(state: State): PlayerGameResult = when {
        state is Burst || this.cards.sum > state.getCards().sum -> PlayerGameResult.WIN
        state.getCards().sum == this.cards.sum -> PlayerGameResult.DRAW
        else -> PlayerGameResult.LOSE
    }

    override fun getBetAmount(): Int = this.betAmount

    override fun getRevenue(state: State): Int = when (getPlayerGameResult(state)) {
        PlayerGameResult.WIN -> this.betAmount
        PlayerGameResult.DRAW -> DRAW_REVENUE
        PlayerGameResult.LOSE -> this.betAmount * LOSE_REVENUE
    }

    companion object {
        const val DRAW_REVENUE = 0
        private const val LOSE_REVENUE = -1
    }
}
