package blackjack.controller

import blackjack.domain.result.DetermineWinnerResult
import blackjack.domain.result.InGameResult
import blackjack.domain.result.InitialDistributionResult
import blackjack.view.model.FinalPlayerStateModel
import blackjack.view.model.PlayerModel
import blackjack.view.output.OutputView

object ViewResultProcessor {
    fun drawInitialDistribution(result: InitialDistributionResult) {
        val model = result.players.allPlayers.map { PlayerModel(it.name.value, it.hand.cards) }
        OutputView.initialDistributionResult(model)
    }

    fun drawPlayerState(result: InGameResult) {
        val model = result.player.let { PlayerModel(it.name.value, it.hand.cards) }
        OutputView.playerCurrentState(model)
    }

    fun drawGameResult(result: DetermineWinnerResult) {
        val model =
            result.players.allPlayers.map { FinalPlayerStateModel(it.name.value, it.hand.cards, it.score.value) }
        OutputView.playerFinalStates(model)
    }
}
