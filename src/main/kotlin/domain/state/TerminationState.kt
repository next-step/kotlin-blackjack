package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

open class TerminationState(private val cards: Cards) : State {
    override fun draw(card: Card): State = this

    override fun stop(): State = this

    override fun getCards(): Cards = this.cards

    override fun getPlayerGameResult(dealerState: State): PlayerGameResult = when {
        dealerState is Burst || this.cards.sum > dealerState.getCards().sum -> PlayerGameResult.WIN
        dealerState.getCards().sum == this.cards.sum -> PlayerGameResult.DRAW
        else -> PlayerGameResult.LOSE
    }

    override fun getRevenueRate(dealerState: State): BigDecimal = when (getPlayerGameResult(dealerState)) {
        PlayerGameResult.WIN -> BigDecimal.ONE
        PlayerGameResult.DRAW -> BigDecimal.ZERO
        PlayerGameResult.LOSE -> BigDecimal(LOSE_REVENUE)
    }

    companion object {
        private const val LOSE_REVENUE = -1
    }
}
