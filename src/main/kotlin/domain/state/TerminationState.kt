package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

open class TerminationState(private val cards: Cards) : State {
    override fun draw(card: Card): State = this

    override fun stop(): State = this

    override fun getCards(): Cards = this.cards

    override fun getPlayerGameResult(state: State): PlayerGameResult = when {
        state is Burst || this.cards.sum > state.getCards().sum -> PlayerGameResult.WIN
        state.getCards().sum == this.cards.sum -> PlayerGameResult.DRAW
        else -> PlayerGameResult.LOSE
    }

    override fun getRevenueRate(state: State): BigDecimal = when (getPlayerGameResult(state)) {
        PlayerGameResult.WIN -> BigDecimal.ONE
        PlayerGameResult.DRAW -> BigDecimal.ZERO
        PlayerGameResult.LOSE -> BigDecimal(LOSE_REVENUE)
    }

    companion object {
        private const val LOSE_REVENUE = -1
    }
}
