package blackjack.controller

import blackjack.domain.result.InitialDistributionResult
import blackjack.domain.result.Result
import blackjack.view.model.PlayerModel
import blackjack.view.output.OutputView

object ResultProcessor {

    fun handle(result: Result) {
        when (result) {
            is InitialDistributionResult -> drawInitialDistribution(result)
        }
    }

    private fun drawInitialDistribution(result: InitialDistributionResult) {
        val model = result.players.allPlayers.map { PlayerModel(it.name.value, it.hand.cards) }
        OutputView.initialDistributionResult(model)
    }
}
