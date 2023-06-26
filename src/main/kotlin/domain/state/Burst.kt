package domain.state

import domain.card.Cards
import domain.player.PlayerGameResult

class Burst(cards: Cards) : TerminationState(cards) {
    override fun getPlayerGameResult(state: State): PlayerGameResult = PlayerGameResult.LOSE
}
