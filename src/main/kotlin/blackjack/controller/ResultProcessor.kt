package blackjack.controller

import blackjack.domain.result.DealInitialCardResult
import blackjack.domain.result.DealToDealerResult
import blackjack.domain.result.DealToPlayerResult
import blackjack.domain.result.GameResult
import blackjack.domain.result.Result

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
