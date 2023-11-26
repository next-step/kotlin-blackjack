package blackjack.controller

import blackjack.domain.result.Result
import blackjack.domain.result.distribution.DealInitialCardResult
import blackjack.domain.result.distribution.DealToDealerResult
import blackjack.domain.result.distribution.DealToPlayerResult
import blackjack.domain.result.game.GameResult

class ResultProcessor {
    fun handle(result: Result) {
        when (result) {
            is DealInitialCardResult -> ViewResultProcessor.drawInitialDistribution(result)
            is DealToPlayerResult -> ViewResultProcessor.drawPlayerState(result)
            is DealToDealerResult -> ViewResultProcessor.drawDealerState(result)
            is GameResult -> ViewResultProcessor.drawGameResult(result)
        }
    }
}
