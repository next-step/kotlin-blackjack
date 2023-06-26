package domain.state

import domain.card.Cards
import domain.player.PlayerGameResult

abstract class ProceedingState(private val cards: Cards) : State {

    final override fun getCards(): Cards = cards

    override fun getPlayerGameResult(state: State): PlayerGameResult {
        throw UnsupportedOperationException("진행중 상태는 지원하지 않음.")
    }
}
