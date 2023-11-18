package blackjack.controller

import blackjack.domain.result.InitialDistributionResult
import blackjack.domain.result.Result

class ResultProcessor {
    fun handle(result: Result) {
        when (result) {
            is InitialDistributionResult -> ViewResultProcessor.drawInitialDistribution(result)
        }
    }
}
