package blackjack.controller

import blackjack.domain.result.Result
import blackjack.domain.result.distribution.DealEndResult
import blackjack.domain.result.distribution.DealInitialCardResult
import blackjack.domain.result.distribution.DealToDealerResult
import blackjack.domain.result.distribution.DealToPlayerResult
import blackjack.domain.result.game.GameEndResult

class ResultProcessor {
    fun handle(result: Result) {
        when (result) {
            is DealInitialCardResult -> ViewResultProcessor.drawInitialDistribution(result)
            is DealToPlayerResult -> ViewResultProcessor.drawPlayerState(result)
            is DealEndResult -> {}
            is DealToDealerResult -> ViewResultProcessor.drawDealerState(result)
            is GameEndResult -> ViewResultProcessor.drawGameResult(result)
        }
    }
}
