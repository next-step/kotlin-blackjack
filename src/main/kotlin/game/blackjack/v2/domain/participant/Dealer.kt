package game.blackjack.v2.domain.participant

import game.blackjack.v2.domain.Blackjack.DEALER_HIT_THRESHOLD
import game.blackjack.v2.domain.Deck

class Dealer : Participant("딜러") {
    private val _gameResultRecord = GameResult.values().associateWith { 0 }.toMutableMap()
    val gameResultRecord: Map<GameResult, Int> get() = _gameResultRecord.toMap()

    fun drawCardIfRequired(
        result: () -> Unit = {}
    ) {
        if (isDrawRequired()) {
            drawCard(Deck.drawOnce())
            result()
        }
    }

    private fun isDrawRequired(): Boolean {
        return getScore() <= DEALER_HIT_THRESHOLD
    }

    fun recordResult(gameResult: GameResult) {
        _gameResultRecord[gameResult] = _gameResultRecord[gameResult]!!.inc()
    }
}
