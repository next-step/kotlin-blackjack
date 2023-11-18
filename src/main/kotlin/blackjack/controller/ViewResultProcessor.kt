package blackjack.controller

import blackjack.domain.result.InitialDistributionResult
import blackjack.view.model.PlayerModel
import blackjack.view.output.OutputView

object ViewResultProcessor {
    fun drawInitialDistribution(result: InitialDistributionResult) {
        val model = result.players.allPlayers.map { PlayerModel(it.name.value, it.hand.cards) }
        OutputView.initialDistributionResult(model)
    }
}
