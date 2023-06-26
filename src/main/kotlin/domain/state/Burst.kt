package domain.state

import domain.card.Cards
import domain.player.PlayerGameResult

class Burst(cards: Cards, betAmount: Int = 0) : TerminationState(cards, betAmount) {
    override fun getPlayerGameResult(state: State): PlayerGameResult = PlayerGameResult.LOSE

    override fun getRevenue(state: State): Int = getBetAmount() * -1
}
