package domain.state

import domain.card.Cards
import domain.player.PlayerGameResult

abstract class ProceedingState(private val cards: Cards, private val betAmount: Int) : State {

    final override fun getCards(): Cards = cards

    override fun getBetAmount(): Int = betAmount

    override fun getPlayerGameResult(state: State): PlayerGameResult {
        throw UnsupportedOperationException("진행중 상태는 지원하지 않음.")
    }

    override fun getRevenue(state: State): Int {
        throw UnsupportedOperationException("진행중 상태는 지원하지 않음.")
    }
}
