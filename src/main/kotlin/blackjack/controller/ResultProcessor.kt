package blackjack.controller

import blackjack.domain.result.DetermineWinnerResult
import blackjack.domain.result.InGameResult
import blackjack.domain.result.InitialDistributionResult
import blackjack.domain.result.Result

class ResultProcessor {
    fun handle(result: Result) {
        when (result) {
            is InitialDistributionResult -> ViewResultProcessor.drawInitialDistribution(result)
            is InGameResult -> ViewResultProcessor.drawPlayerState(result)
            is DetermineWinnerResult -> ViewResultProcessor.drawGameResult(result)
        }
    }
}
