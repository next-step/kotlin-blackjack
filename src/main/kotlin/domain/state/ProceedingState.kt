package domain.state

import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

abstract class ProceedingState(private val cards: Cards) : State {

    final override fun getCards(): Cards = cards

    override fun getPlayerGameResult(state: State): PlayerGameResult {
        throw UnsupportedOperationException("진행중 상태는 지원하지 않음.")
    }

    override fun getRevenueRate(state: State): BigDecimal {
        throw UnsupportedOperationException("진행중 상태는 지원하지 않음.")
    }
}
